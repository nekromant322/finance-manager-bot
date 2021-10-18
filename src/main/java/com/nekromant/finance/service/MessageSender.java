package com.nekromant.finance.service;

import com.nekromant.finance.FinanceManagerBot;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendInvoice;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

/**
 * Нужен для отправки сообщений и не является командой по своей сути
 */
@Service
public class MessageSender {

    @Autowired
    private ApplicationContext applicationContext;

    @SneakyThrows
    public void sendMessage(String text, String chatId) {
        sendMessage(SendMessage.builder()
                .text(text)
                .chatId(chatId)
                .build());
    }

    @SneakyThrows
    public void sendMessage(SendMessage sendMessage) {
        FinanceManagerBot bot = applicationContext.getBean(FinanceManagerBot.class);
        bot.execute(sendMessage);
    }

    @SneakyThrows
    public void sendInvoice(String chatId, String payload, String providerToken) {
        SendInvoice sendInvoice = new SendInvoice();
        sendInvoice.setChatId(Integer.valueOf(chatId));
        sendInvoice.setPayload(payload);
        sendInvoice.setProviderToken(providerToken);
        FinanceManagerBot bot = applicationContext.getBean(FinanceManagerBot.class);
        bot.execute(sendInvoice);
    }
}
