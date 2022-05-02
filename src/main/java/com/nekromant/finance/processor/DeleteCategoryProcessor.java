package com.nekromant.finance.processor;

import com.nekromant.finance.contants.CallBackPrefix;
import com.nekromant.finance.repository.CategoryRepository;
import com.nekromant.finance.service.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class DeleteCategoryProcessor implements CallBackProcessor {
  @Autowired private CategoryRepository categoryRepository;
  @Autowired MessageSender messageSender;

  @Override
  public void process(Update update) {
    categoryRepository.deleteCategoryById(
        Long.parseLong(update.getCallbackQuery().getData().split(" ")[1]));
    categoryRepository.deleteById(
        Long.parseLong(update.getCallbackQuery().getData().split(" ")[1]));
    messageSender.sendMessage(
        "Категория удалена", String.valueOf(update.getCallbackQuery().getMessage().getChatId()));
  }

  @Override
  public String getPrefix() {
    return CallBackPrefix.DELETE_CATEGORY.getAlias();
  }
}
