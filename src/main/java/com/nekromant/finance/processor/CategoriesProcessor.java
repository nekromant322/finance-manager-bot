package com.nekromant.finance.processor;

import com.nekromant.finance.FinanceManagerBot;
import com.nekromant.finance.contants.CallBackData;
import com.nekromant.finance.contants.Command;
import com.nekromant.finance.models.Category;
import com.nekromant.finance.models.FinanceClient;
import com.nekromant.finance.repository.CategoryRepository;
import com.nekromant.finance.repository.FinanceClientRepository;
import com.nekromant.finance.service.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CategoriesProcessor implements CallBackProcessor {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private MessageSender messageSender;
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void process(Update update) throws TelegramApiException {
        String data = update.getCallbackQuery().getData();
        FinanceManagerBot bot = applicationContext.getBean(FinanceManagerBot.class);
        bot.execute(new DeleteMessage(data.split(" ")[2], update.getCallbackQuery().getMessage().getMessageId()));
        List<InlineKeyboardButton> buttons = new ArrayList<>();

        InlineKeyboardButton keywordsButton = new InlineKeyboardButton();
        keywordsButton.setText("Ключевые слова");
        keywordsButton.setCallbackData("!get_keywords_info " + Long.parseLong(data.split(" ")[1]));

        InlineKeyboardButton editNameButton = new InlineKeyboardButton();
        editNameButton.setText("Изменить название");
        editNameButton.setCallbackData("!edit_name " + Long.parseLong(data.split(" ")[1]));

        InlineKeyboardButton deleteButton = new InlineKeyboardButton();
        deleteButton.setText("Удалить категорию");
        deleteButton.setCallbackData("!delete_category " + Long.parseLong(data.split(" ")[1]));

        InlineKeyboardButton previousButton = new InlineKeyboardButton();
        previousButton.setText("Назад");
        previousButton.setCallbackData(Command.CATEGORIES.getAlias());

        buttons.add(keywordsButton);
        buttons.add(editNameButton);
        buttons.add(deleteButton);
        buttons.add(previousButton);

        Optional<Category> optionalCategory = categoryRepository.findById(Long.parseLong(data.split(" ")[1]));
        if (optionalCategory.isPresent()) {
            messageSender.sendMessageWithInlineButtons(Long.parseLong(data.split(" ")[2]),
                    "Выбранная категория: " + optionalCategory.get().getName(),
                    buttons, 1);
        }
    }

    @Override
    public String getPrefix() {
        return "!get_category_info";
    }
}
