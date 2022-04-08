package com.nekromant.finance.processor;


import com.nekromant.finance.contants.CallBackPrefix;
import org.aspectj.weaver.ast.Call;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class EditNameCategoryProcessor implements CallBackProcessor {
    @Override
    public void process(Update update) throws TelegramApiException {
        String data = update.getCallbackQuery().getData();
        System.out.println(data);

    }

    @Override
    public String getPrefix() {
        return CallBackPrefix.EDIT_NAME_CATEGORY.getAlias();
    }
}
