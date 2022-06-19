package com.nekromant.finance.service;

import com.nekromant.finance.contants.Errors;
import com.nekromant.finance.exception.CommandExecuteException;
import com.nekromant.finance.models.NonCommandInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class InputParser {

  public NonCommandInput parseNonCommandInput(String input) {
    NonCommandInput result = new NonCommandInput();
    String[] splitInput = null;
    if (!input.isBlank()) {
      splitInput = input.split(" ");
    }
    assert splitInput != null;
    if (splitInput.length > 3) {
      throw new CommandExecuteException(Errors.COMMAND_FORMAT);
    }

    // price keyword comment
    try {
      result.setMoney(Double.parseDouble(splitInput[0]));
      result.setText(splitInput[1]);

      if (splitInput.length == 3) {
        result.setComment(splitInput[2]);
      }
    } catch (Exception e) {
      log.error("Неверный формат команды");
      return null;
    }
    return result;
  }
}
