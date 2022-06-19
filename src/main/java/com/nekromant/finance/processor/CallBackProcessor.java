package com.nekromant.finance.processor;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface CallBackProcessor {

  void process(Update update) throws TelegramApiException;
  String getPrefix();
}
