package com.nekromant.finance.processor;

import com.nekromant.finance.FinanceManagerBot;
import com.nekromant.finance.contants.CallBackPrefix;
import com.nekromant.finance.contants.Command;
import com.nekromant.finance.contants.Title;
import com.nekromant.finance.models.Category;
import com.nekromant.finance.repository.CategoryRepository;
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
        bot.execute(new DeleteMessage(update.getCallbackQuery().getMessage().getChatId().toString(), update.getCallbackQuery().getMessage().getMessageId()));
        List<InlineKeyboardButton> buttons = new ArrayList<>();

        InlineKeyboardButton keywordsButton = new InlineKeyboardButton();
        keywordsButton.setText(Title.KEYWORDS.getText());
        keywordsButton.setCallbackData(CallBackPrefix.GET_KEYWORDS.getAlias() + " " + Long.parseLong(data.split(" ")[1]));

        InlineKeyboardButton editNameButton = new InlineKeyboardButton();
        editNameButton.setText(Title.EDIT_NAME.getText());
        editNameButton.setCallbackData(CallBackPrefix.EDIT_NAME_CATEGORY.getAlias() + " " + Long.parseLong(data.split(" ")[1]));

        InlineKeyboardButton deleteButton = new InlineKeyboardButton();
        deleteButton.setText(Title.DELETE_CATEGORY.getText());
        deleteButton.setCallbackData(CallBackPrefix.DELETE_CATEGORY.getAlias() + " " + Long.parseLong(data.split(" ")[1]));

        buttons.add(keywordsButton);
        buttons.add(editNameButton);
        buttons.add(deleteButton);

        Optional<Category> optionalCategory = categoryRepository.findById(Long.parseLong(data.split(" ")[1]));
        if (optionalCategory.isPresent()) {
            messageSender.sendMessageWithInlineButtons(update.getCallbackQuery().getMessage().getChatId(),
                    "Выбранная категория: " + optionalCategory.get().getName(), buttons, 1);

            messageSender.sendMessage("Для возврата к списку категорий введите команду /categories",
                    String.valueOf(update.getCallbackQuery().getMessage().getChatId()));
        }
    }

    @Override
    public String getPrefix() {
        return CallBackPrefix.GET_CATEGORY_INFO.getAlias();
    }
}
