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
public class EditKeyWordsProcessor implements CallBackProcessor {

  private final MessageSender messageSender;
  private final ApplicationContext applicationContext;

  @Override
  public void process(Update update) throws TelegramApiException {
    FinanceManagerBot bot = applicationContext.getBean(FinanceManagerBot.class);
    bot.execute(
        new DeleteMessage(
            update.getCallbackQuery().getMessage().getChatId().toString(),
            update.getCallbackQuery().getMessage().getMessageId()));
    messageSender.sendMessage(
        "Для добавления ключевых слов необходимо ввести команду : /add_keywords {Название категории} {Ключевое слово,..}",
        String.valueOf(update.getCallbackQuery().getMessage().getChatId()));
  }

  @Override
  public String getPrefix() {
    return CallBackPrefix.EDIT_KEYWORDS.getAlias();
  }
}
