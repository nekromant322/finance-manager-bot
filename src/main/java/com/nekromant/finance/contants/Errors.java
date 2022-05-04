package com.nekromant.finance.contants;

import lombok.Getter;

public enum Errors {
  ERROR("Что-то пошло не так\n"),
  UNKNOWN_COMMAND("Не понимаю команду"),
  NOT_OWNER("Ты не владелец бота"),
  COMMAND_FORMAT("Неверный формат команды"),
  USER_NOT_FOUND("Пользователь не найден"),
  CATEGORY_NOT_FOUND("Категория не найдена");

  @Getter private final String text;

  Errors(String text) {
    this.text = text;
  }
}
