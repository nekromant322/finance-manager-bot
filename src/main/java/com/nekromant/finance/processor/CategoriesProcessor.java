package com.nekromant.finance.processor;

import com.nekromant.finance.models.Category;
import com.nekromant.finance.models.FinanceClient;
import com.nekromant.finance.repository.CategoryRepository;
import com.nekromant.finance.repository.FinanceClientRepository;
import com.nekromant.finance.service.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
    private FinanceClientRepository financeClientRepository;

    @Override
    public void process(Update update) throws TelegramApiException {
        String data = update.getCallbackQuery().getData();
        List<InlineKeyboardButton> buttons = new ArrayList<>();

        InlineKeyboardButton editNameButton = new InlineKeyboardButton();
        editNameButton.setText("Изменить название");
        editNameButton.setCallbackData("!edit_name " + Long.parseLong(data.split(" ")[1]));

        InlineKeyboardButton editKeywordsButton = new InlineKeyboardButton();
        editKeywordsButton.setText("Изменить ключевые слова");
        editKeywordsButton.setCallbackData("!edit_keywords " + Long.parseLong(data.split(" ")[1]));

        InlineKeyboardButton deleteButton = new InlineKeyboardButton();
        deleteButton.setText("Удалить категорию");
        deleteButton.setCallbackData("!delete_category " + Long.parseLong(data.split(" ")[1]));

        buttons.add(editNameButton);
        buttons.add(editKeywordsButton);
        buttons.add(deleteButton);

        Optional<Category> optionalCategory = categoryRepository.findById(Long.parseLong(data.split(" ")[1]));
        if (optionalCategory.isPresent()) {
            List<String> keywords = categoryRepository.findKeywordsByCategoryId(optionalCategory.get().getId());
            messageSender.sendMessage(keywords.toString(), String.valueOf(Long.parseLong(data.split(" ")[2])));
        }
        messageSender.sendMessageWithInlineButtons(Long.parseLong(data.split(" ")[2]),"Возможные действия", buttons, 1);


    }

    @Override
    public String getPrefix() {
        return "!get_category_info";
    }
}
