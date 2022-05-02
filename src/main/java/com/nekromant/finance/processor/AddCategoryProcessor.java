package com.nekromant.finance.processor;

import com.nekromant.finance.contants.CallBackPrefix;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class AddCategoryProcessor implements CallBackProcessor {
    @Override
    public void process(Update update) throws TelegramApiException {

    }

    @Override
    public String getPrefix() {
        return CallBackPrefix.ADD_CATEGORY.getAlias();
    }
}
