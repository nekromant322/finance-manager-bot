package com.nekromant.finance.commands;

import com.nekromant.finance.contants.CallBackPrefix;
import com.nekromant.finance.contants.Title;
import com.nekromant.finance.models.Category;
import com.nekromant.finance.models.FinanceClient;
import com.nekromant.finance.repository.FinanceClientRepository;
import com.nekromant.finance.service.MessageSender;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.nekromant.finance.contants.Command.CATEGORIES;

@Component
public class CategoriesCommand extends FinanceManagerCommand {
  @Autowired private FinanceClientRepository financeClientRepository;
  @Autowired private MessageSender messageSender;

  public CategoriesCommand() {
    super(CATEGORIES.getAlias(), CATEGORIES.getDescription());
  }

  @SneakyThrows
  @Override
  public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
    FinanceClient financeClient;
    Optional<FinanceClient> optionalFinanceClient = financeClientRepository.findById(chat.getId());

    if (optionalFinanceClient.isPresent()) {
      financeClient = optionalFinanceClient.get();
      List<Category> categories = financeClient.getCategories();

      List<InlineKeyboardButton> buttons =
          categories.stream()
              .map(
                  v -> {
                    InlineKeyboardButton inlineKeyboardButton =
                        new InlineKeyboardButton(v.getName());
                    inlineKeyboardButton.setCallbackData(
                        CallBackPrefix.GET_CATEGORY_INFO.getAlias() + " " + v.getId());
                    return inlineKeyboardButton;
                  })
              .collect(Collectors.toList());

      InlineKeyboardButton inlineKeyboardButton =
          new InlineKeyboardButton(Title.ADD_CATEGORY.getText());
      inlineKeyboardButton.setCallbackData(CallBackPrefix.ADD_CATEGORY.getAlias());
      buttons.add(inlineKeyboardButton);

      messageSender.sendMessageWithInlineButtons(
          chat.getId(), Title.MANAGE_CATEGORIES.getText(), buttons, 3);
    }
  }
}
