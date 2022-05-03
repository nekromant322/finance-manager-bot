package com.nekromant.finance.processor;

import com.nekromant.finance.FinanceManagerBot;
import com.nekromant.finance.contants.CallBackPrefix;
import com.nekromant.finance.contants.Title;
import com.nekromant.finance.models.Category;
import com.nekromant.finance.repository.CategoryRepository;
import com.nekromant.finance.service.MessageSender;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class KeywordsProcessor implements CallBackProcessor {
  private final CategoryRepository categoryRepository;
  private final MessageSender messageSender;
  private final ApplicationContext applicationContext;

  @Override
  public void process(Update update) throws TelegramApiException {
    String data = update.getCallbackQuery().getData();
    Optional<Category> optionalCategory =
        categoryRepository.findById(Long.parseLong(data.split(" ")[1]));

    FinanceManagerBot bot = applicationContext.getBean(FinanceManagerBot.class);
    bot.execute(
        new DeleteMessage(
            update.getCallbackQuery().getMessage().getChatId().toString(),
            update.getCallbackQuery().getMessage().getMessageId()));
    List<InlineKeyboardButton> buttons = new ArrayList<>();

    InlineKeyboardButton editKeywordsButton = new InlineKeyboardButton();
    editKeywordsButton.setText(Title.EDIT_KEYWORDS.getText());
    editKeywordsButton.setCallbackData(CallBackPrefix.EDIT_KEYWORDS.getAlias());

    InlineKeyboardButton previousButton = new InlineKeyboardButton();
    previousButton.setText(Title.BACK.getText());
    previousButton.setCallbackData(
        CallBackPrefix.GET_CATEGORY_INFO.getAlias() + " " + Long.parseLong(data.split(" ")[1]));

    buttons.add(editKeywordsButton);
    buttons.add(previousButton);

    if (optionalCategory.isPresent()) {
      List<String> keywords =
          categoryRepository.findKeywordsByCategoryId(optionalCategory.get().getId());
      if (keywords.size() != 0) {
        messageSender.sendMessageWithInlineButtons(
            update.getCallbackQuery().getMessage().getChatId(),
            keywords.toString().replace("[", "").replace("]", ""),
            buttons,
            1);
      } else {
        messageSender.sendMessage(
            "Ключевых слов пока нет",
            String.valueOf(update.getCallbackQuery().getMessage().getChatId()));
      }
    }
  }

  @Override
  public String getPrefix() {
    return CallBackPrefix.GET_KEYWORDS.getAlias();
  }
}
