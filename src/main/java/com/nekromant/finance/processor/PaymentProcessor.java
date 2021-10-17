package com.nekromant.finance.processor;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class PaymentProcessor implements CallBackProcessor {
    @Override
    public void process(Update update) {

    }

    @Override
    public String getPrefix() {
        return "!payment";
    }
}
