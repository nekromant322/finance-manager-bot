package com.nekromant.finance;

import com.nekromant.finance.commands.FinanceManagerCommand;
import com.nekromant.finance.contants.Errors;
import com.nekromant.finance.exception.CommandExecuteException;
import com.nekromant.finance.models.Category;
import com.nekromant.finance.models.FinanceClient;
import com.nekromant.finance.models.Type;
import com.nekromant.finance.processor.CallBackProcessor;
import com.nekromant.finance.repository.CategoryRepository;
import com.nekromant.finance.repository.FinanceClientRepository;
import com.nekromant.finance.service.MessageSender;
import com.nekromant.finance.service.NonCommandInputService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class FinanceManagerBot extends TelegramLongPollingCommandBot {

  @Value("${bot.name}")
  private String botName;

  @Value("${bot.token}")
  private String botToken;

  private final NonCommandInputService nonCommandInputService;

  @Autowired private FinanceClientRepository clientRepository;
  @Autowired private CategoryRepository categoryRepository;

  @Autowired private MessageSender messageSender;

  private final HashMap<String, CallBackProcessor> callBackProcessors = new HashMap<>();

  @Autowired
  public FinanceManagerBot(
      List<FinanceManagerCommand> allCommands,
      List<CallBackProcessor> processors,
      NonCommandInputService nonCommandInputService) {
    super();
    allCommands.forEach(this::register);
    processors.forEach(x -> callBackProcessors.put(x.getPrefix(), x));
    this.nonCommandInputService = nonCommandInputService;
  }

  @Override
  public String getBotUsername() {
    return botName;
  }

  @SneakyThrows
  @Override
  public void processNonCommandUpdate(Update update) {
    try {
      if (update.hasMessage()) {
        Message message = update.getMessage();
        String text = message.getText();
        if (text.contains("rename")) {
          // переименовать категорию
          renameCategory(update, text);
          return;
        }
        if (text.contains("add_category")) {
          // добавить категорию
          addCategory(update, text);
          return;
        }
        if (text.contains("add_keywords")) {
          // добавить ключевые слова
          addKeyWords(update, text);
          return;
        }
        if (text.contains("delete_keyword")) {
          // удалить ключевое слово из категории
        }
        nonCommandInputService.processNonCommandInput(message.getText(), message.getChatId());
      }
      if (update.hasCallbackQuery()) {
        // выбор нужного процессора по префиксу
        String data = update.getCallbackQuery().getData();
        CallBackProcessor callBackProcessor = callBackProcessors.get(data.split(" ")[0]);
        callBackProcessor.process(update);
      }
    } catch (CommandExecuteException ex) {
      log.error("Ошибка выполнения команды: {}", ex.getError().getText());
      sendErrorMessage(update, ex.getError());
    }
  }

  @Override
  public String getBotToken() {
    return botToken;
  }

  private void sendErrorMessage(Update update, Errors error) {
    SendMessage sendMessage = new SendMessage();
    sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
    sendMessage.setText(error.getText());
    try {
      execute(sendMessage);
    } catch (TelegramApiException e) {
      e.printStackTrace();
    }
  }

  @SneakyThrows
  public void sendMessage(String chatId, String text) {
    SendMessage message = new SendMessage();
    message.setText(text);
    message.setChatId(chatId);
    execute(message);
  }

  private void renameCategory(Update update, String text) {
    Long chatId = update.getMessage().getChatId();
    FinanceClient financeClient = clientRepository.findByChatId(chatId);
    List<Category> categories = financeClient.getCategories();
    String oldName = null;
    Category category = null;
    for (Category value : categories) {
      if (text.contains(value.getName())) {
        text = text.replaceFirst(value.getName(), "");
        oldName = value.getName();
        category = value;
        break;
      }
    }
    if (category == null) {
      messageSender.sendMessage(
          "Категория с таким именем не найдена, попробуйте еще раз",
          String.valueOf(update.getMessage().getChatId()));
    }
    String newName = text.replace("/rename", "");
    category.setName(newName);
    categoryRepository.save(category);
    messageSender.sendMessage(
        String.format("Категория %s переименована в %s", oldName, newName),
        String.valueOf(update.getMessage().getChatId()));
  }

  private void addCategory(Update update, String text) {
    // add_category name type
    Long chatId = update.getMessage().getChatId();
    FinanceClient financeClient = clientRepository.findByChatId(chatId);
    List<Category> categories = financeClient.getCategories();
    String[] command = text.split(" ");
    if (command.length != 3) {
      throw new CommandExecuteException(Errors.COMMAND_FORMAT);
    }
    Category category = new Category(command[1], null, Type.valueOf(command[2]));
    categories.add(categoryRepository.save(category));
    financeClient.setCategories(categories);
    clientRepository.save(financeClient);
    messageSender.sendMessage(
        String.format("Категория %s добавлена ", category.getName()),
        String.valueOf(update.getMessage().getChatId()));
  }

  private void addKeyWords(Update update, String text) {
    // add_keywords categoryName keyword0, keyword1, keyword2
    Long chatId = update.getMessage().getChatId();
    FinanceClient financeClient = clientRepository.findByChatId(chatId);
    List<Category> categories = financeClient.getCategories();
    Category category = null;
    for (Category item : categories) {
      if (text.contains(item.getName())) {
        category = item;
        text = (text.replace("/add_keywords", "")).replaceFirst(item.getName(), "");
      }
    }
    List<String> keywords = List.of(text.replace(" ", "").split(","));
    keywords = keywords.stream().distinct().collect(Collectors.toList());
    List<String> oldKeyWords = categoryRepository.findKeywordsByCategoryId(category.getId());
    oldKeyWords.addAll(keywords);
    category.setKeywords(oldKeyWords.stream().distinct().collect(Collectors.toList()));
    categoryRepository.save(category);
  }
}
