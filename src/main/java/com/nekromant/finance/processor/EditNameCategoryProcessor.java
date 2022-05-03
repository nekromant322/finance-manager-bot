package com.nekromant.finance.processor;

import com.nekromant.finance.FinanceManagerBot;
import com.nekromant.finance.contants.CallBackPrefix;
import com.nekromant.finance.service.MessageSender;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@RequiredArgsConstructor
public class EditNameCategoryProcessor implements CallBackProcessor {

  private final MessageSender messageSender;
  private final ApplicationContext applicationContext;

  @Override
  public void process(Update update) throws TelegramApiException {
    FinanceManagerBot bot = applicationContext.getBean(FinanceManagerBot.class);
    bot.execute(
        new DeleteMessage(
            update.getCallbackQuery().getMessage().getChatId().toString(),
            update.getCallbackQuery().getMessage().getMessageId()));
    bot.execute(
        new DeleteMessage(
            update.getCallbackQuery().getMessage().getChatId().toString(),
            update.getCallbackQuery().getMessage().getMessageId() + 1));

    messageSender.sendMessage(
        "Для переименования названия категории введите команду в формате:"
            + " /rename {название категории} {новое название}",
        String.valueOf(update.getCallbackQuery().getMessage().getChatId()));
  }

  @Override
  public String getPrefix() {
    return CallBackPrefix.EDIT_NAME_CATEGORY.getAlias();
  }
}
