package com.nekromant.finance.commands;

import com.nekromant.finance.models.Category;
import com.nekromant.finance.models.FinanceClient;
import com.nekromant.finance.repository.FinanceClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.nekromant.finance.contants.Command.CATEGORIES;

@Component
public class CategoriesCommand extends FinanceManagerCommand {

    @Autowired
    FinanceClientRepository financeClientRepository;

    public CategoriesCommand() {
        super(CATEGORIES.getAlias(), CATEGORIES.getDescription());
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        SendMessage message = new SendMessage();
        message.setChatId(chat.getId().toString());
        message.setText("Управление категориями");
        FinanceClient financeClient;
        Optional<FinanceClient> optionalFinanceClient = financeClientRepository.findById(chat.getId());

        if (optionalFinanceClient.isPresent()) {
            financeClient = optionalFinanceClient.get();
            List<Category> categories = financeClient.getCategories();

            InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

            List<InlineKeyboardButton> keyboardButtons = new ArrayList<>();
            List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

            for (int i = 0; i < categories.size(); i++) {
                if (i % 2 == 0) {
                    keyboardButtons = new ArrayList<>();
                    rowList.add(keyboardButtons);
                }
                InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
                inlineKeyboardButton.setText(categories.get(i).getName());
                inlineKeyboardButton.setCallbackData(categories.get(i).getId().toString());
                keyboardButtons.add(inlineKeyboardButton);


            }

            inlineKeyboardMarkup.setKeyboard(rowList);
            message.setReplyMarkup(inlineKeyboardMarkup);
            execute(absSender, message, user);
        }
    }
}
