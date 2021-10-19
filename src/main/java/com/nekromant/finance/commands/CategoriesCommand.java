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
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<Category> rowList = new ArrayList<>();
        Optional<FinanceClient> optionalFinanceClient = financeClientRepository.findById(chat.getId());
        FinanceClient financeClient = optionalFinanceClient.get();
        rowList = financeClient.getCategories();
        SendMessage message = new SendMessage();
        message.setChatId(chat.getId().toString());
        message.setText("Редактирование категорий");
        execute(absSender, message, user);
    }
}
