package com.nekromant.finance.processor;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface CallBackProcessor {

    void process(Update update);

    String getPrefix();

}
