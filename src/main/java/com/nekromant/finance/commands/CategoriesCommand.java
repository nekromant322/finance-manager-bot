package com.nekromant.finance.commands;

import com.nekromant.finance.models.Category;
import com.nekromant.finance.models.FinanceClient;
import com.nekromant.finance.repository.FinanceClientRepository;
import com.nekromant.finance.service.MessageSender;
import lombok.SneakyThrows;
import org.checkerframework.checker.units.qual.A;
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

    @Autowired
    MessageSender messageSender;

    public CategoriesCommand() {
        super(CATEGORIES.getAlias(), CATEGORIES.getDescription());
    }

    @SneakyThrows
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

            List<InlineKeyboardButton> keyboardButtons = new ArrayList<>();

            for (Category category : categories) {
                InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
                inlineKeyboardButton.setText(category.getName());
                inlineKeyboardButton.setCallbackData(category.getId().toString());
                keyboardButtons.add(inlineKeyboardButton);
            }
            messageSender.sendMessageWithInlineButtons(chat.getId(),"Редактирование категорий", keyboardButtons, 3);

            //execute(absSender, message, user);
        }
    }
}
