package com.nekromant.finance.contants;

public enum Command {
  START("start", "Начать работу с ботом"),
  HOW_TO_USE("how_to_use", "Краткий гайд для работы с ботом"),
  CATEGORIES("categories", "Редактировать категории");

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
