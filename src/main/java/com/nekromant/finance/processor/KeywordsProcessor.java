package com.nekromant.finance.processor;

import com.nekromant.finance.contants.CallBackData;
import com.nekromant.finance.models.Category;
import com.nekromant.finance.repository.CategoryRepository;
import com.nekromant.finance.service.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;
import java.util.Optional;
@Component
public class KeywordsProcessor implements CallBackProcessor {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private MessageSender messageSender;

    @Override
    public void process(Update update)  {
        String data = update.getCallbackQuery().getData();
        Optional<Category> optionalCategory = categoryRepository.findById(Long.parseLong(data.split(" ")[1]));
        if (optionalCategory.isPresent()) {
            List<String> keywords = categoryRepository.findKeywordsByCategoryId(optionalCategory.get().getId());
            messageSender.sendMessage(keywords.toString(), String.valueOf(update.getCallbackQuery().getMessage().getChatId()));
        }
    }

    @Override
    public String getPrefix() {
        return CallBackData.GET_KEYWORDS.getAlias();
    }
}
