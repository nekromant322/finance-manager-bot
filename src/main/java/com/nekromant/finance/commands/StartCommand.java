package com.nekromant.finance.commands;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.util.ArrayList;
import java.util.List;

import static com.nekromant.finance.contants.Command.START;

@Component
public class StartCommand extends FinanceManagerCommand {


    public StartCommand() {
        super(START.getAlias(), START.getDescription());
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {

        //TODO убрать семпловые кнопки
        SendMessage message = new SendMessage();
        message.setChatId(chat.getId().toString());
        message.setText("Добро пожаловать");

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRowList = new ArrayList<>();

        KeyboardRow keyboardRow = new KeyboardRow();
        KeyboardButton keyboardButton = new KeyboardButton();
        keyboardButton.setText("/start");
        keyboardRow.add(keyboardButton);
        KeyboardButton keyboardButton1 = new KeyboardButton();
        keyboardButton1.setText("Расход");
        keyboardRow.add(keyboardButton1);
        KeyboardButton keyboardButton2 = new KeyboardButton();
        keyboardButton2.setText("Подписки");
        keyboardRow.add(keyboardButton2);
        keyboardRowList.add(keyboardRow);


        replyKeyboardMarkup.setKeyboard(keyboardRowList);
        replyKeyboardMarkup.setResizeKeyboard(true);
        message.setReplyMarkup(replyKeyboardMarkup);

        execute(absSender, message, user);
    }
}
