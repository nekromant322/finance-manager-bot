package com.nekromant.finance.service;

import com.nekromant.finance.contants.Errors;
import com.nekromant.finance.exception.CommandExecuteException;
import com.nekromant.finance.models.NonCommandInput;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class InputParser {

  public NonCommandInput parseNonCommandInput(String input) {
    NonCommandInput result = new NonCommandInput();

    String[] splitInput =
        Arrays.stream(input.split(" ")).filter(String::isBlank).toArray(String[]::new);

    if (splitInput.length != 2) {
      throw new CommandExecuteException(Errors.COMMAND_FORMAT);
    }

    Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?");
    for (int i = 0; i < splitInput.length; i++) {
      Matcher m = pattern.matcher(splitInput[i]);
      if (m.find()) {
        result.setMoney(Double.parseDouble(m.group()));
        result.setText(i == 0 ? splitInput[1].trim() : splitInput[0].trim());
      }
    }

    return result;
  }
}
