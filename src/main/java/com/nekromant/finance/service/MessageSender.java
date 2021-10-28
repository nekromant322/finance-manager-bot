package com.nekromant.finance.service;

import com.nekromant.finance.FinanceManagerBot;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendInvoice;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.stickers.AddStickerToSet;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

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

    public void sendMessageWithInlineButtons(Long chatId,String messageText, List<InlineKeyboardButton> list, int rowNumbers) throws TelegramApiException {
        SendMessage message = new SendMessage();
        message.setChatId(chatId.toString());
        message.setText(messageText);
        List<InlineKeyboardButton> tempList = new ArrayList<>();
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            if (i % rowNumbers == 0) {
                tempList = new ArrayList<>();
                rowList.add(tempList);
            }
            tempList.add(list.get(i));
        }
        inlineKeyboardMarkup.setKeyboard(rowList);
        message.setReplyMarkup(inlineKeyboardMarkup);
        FinanceManagerBot bot = applicationContext.getBean(FinanceManagerBot.class);
        bot.execute(message);


    }

}
