package com.nekromant.finance.processor;


import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class EditNameCategoryProcessor implements CallBackProcessor {
    @Override
    public void process(Update update) throws TelegramApiException {
        String data = update.getCallbackQuery().getData();

    }

    @Override
    public String getPrefix() {
        return null;
    }
}
