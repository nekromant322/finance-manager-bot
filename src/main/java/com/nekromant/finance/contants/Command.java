package com.nekromant.finance.contants;

public enum Command {
  START("start", "Начать работу с ботом"),
  CATEGORIES("categories", "Редактировать категории"),
  ADD_CATEGORY("add_category","Добавление категории"),
  DELETE_CATEGORY("delete_category","Удаление категории");

  private String alias;
  private String description;

  Command(String alias, String description) {
    this.alias = alias;
    this.description = description;
  }

  public String getAlias() {
    return alias;
  }

  public String getDescription() {
    return description;
  }
}
