package com.nekromant.finance.commands;

import com.nekromant.finance.models.FinanceClient;
import com.nekromant.finance.config.properties.DefaultCategoriesProperties;
import com.nekromant.finance.repository.FinanceClientRepository;
import com.nekromant.finance.service.FinanceClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.util.*;

import static com.nekromant.finance.contants.Command.START;

@Component
public class StartCommand extends FinanceManagerCommand {
    @Autowired
    private FinanceClientRepository financeClientRepository;

    @Autowired
    private DefaultCategoriesProperties defaultCategories;

    @Autowired
    private FinanceClientService financeClientService;

    public StartCommand() {
        super(START.getAlias(), START.getDescription());
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {

        //TODO убрать семпловые кнопки
        SendMessage message = new SendMessage();
        message.setChatId(chat.getId().toString());
        message.setText("Добро пожаловать");


        Optional<FinanceClient> optionalFinanceClient = financeClientRepository.findById(chat.getId());
        if (optionalFinanceClient.isEmpty()) {
            financeClientService.saveUser((new FinanceClient(chat.getId(),
                    List.of(user.getUserName()),
                    defaultCategories.getCategories())));
        }

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonRow = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setText("Knopka");
        inlineKeyboardButton.setCallbackData("callbackData");

        keyboardButtonRow.add(inlineKeyboardButton);
        rowList.add(keyboardButtonRow);

        inlineKeyboardMarkup.setKeyboard(rowList);

        message.setReplyMarkup(inlineKeyboardMarkup);
        execute(absSender, message, user);
    }
}
