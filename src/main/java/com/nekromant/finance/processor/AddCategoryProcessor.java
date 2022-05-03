package com.nekromant.finance.processor;

import com.nekromant.finance.contants.CallBackPrefix;
import com.nekromant.finance.repository.CategoryRepository;
import com.nekromant.finance.service.MessageSender;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@RequiredArgsConstructor
public class AddCategoryProcessor implements CallBackProcessor {
  private final CategoryRepository categoryRepository;
  private final MessageSender messageSender;
  private final ApplicationContext applicationContext;

  @Override
  public void process(Update update) throws TelegramApiException {
    messageSender.sendMessage(
        "Для добавления категории введите команду /add_category {название новой категории} {DEBIT/CREDIT}",
        String.valueOf(update.getCallbackQuery().getMessage().getChatId()));
  }

  @Override
  public String getPrefix() {
    return CallBackPrefix.ADD_CATEGORY.getAlias();
  }
}
