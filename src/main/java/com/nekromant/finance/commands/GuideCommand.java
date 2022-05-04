package com.nekromant.finance.commands;

import com.nekromant.finance.service.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

import static com.nekromant.finance.contants.Command.HOW_TO_USE;

@Component
public class GuideCommand extends FinanceManagerCommand {

  @Autowired private MessageSender messageSender;

  public GuideCommand() {
    super(HOW_TO_USE.getAlias(), HOW_TO_USE.getDescription());
  }

  @Override
  public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
    messageSender.sendMessage("", String.valueOf(chat.getId()));
  }
}
