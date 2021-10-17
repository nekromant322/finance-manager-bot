package com.nekromant.finance.service;

import com.nekromant.finance.FinanceManagerBot;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

/**
 * Нужен для отправки сообщений и не является командой по своей сути
 */
@Service
public class SimpleMessageSender {


    @Autowired
    private ApplicationContext applicationContext;

    @SneakyThrows
    public void sendMessage(String text, String chatId) {
        FinanceManagerBot bot = applicationContext.getBean(FinanceManagerBot.class);
        bot.execute(
                SendMessage.builder()
                        .text(text)
                        .chatId(chatId)
                        .build()
        );
    }
}
