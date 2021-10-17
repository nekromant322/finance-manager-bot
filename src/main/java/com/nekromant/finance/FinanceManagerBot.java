package com.nekromant.finance;

import com.nekromant.finance.commands.FinanceManagerCommand;
import com.nekromant.finance.contants.Errors;
import com.nekromant.finance.exception.CommandExecuteException;
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

import java.util.List;


@Slf4j
@Component
public class FinanceManagerBot extends TelegramLongPollingCommandBot {

    @Value("${bot.name}")
    private String botName;

    @Value("${bot.token}")
    private String botToken;

    private final NonCommandInputService nonCommandInputService;

    @Autowired
    public FinanceManagerBot(List<FinanceManagerCommand> allCommands, NonCommandInputService nonCommandInputService) {
        super();
        allCommands.forEach(this::register);
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
                nonCommandInputService.processNonCommandInput(message.getText(), message.getChatId());
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
}
