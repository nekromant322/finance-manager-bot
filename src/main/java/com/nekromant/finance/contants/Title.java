package com.nekromant.finance.contants;

import lombok.Getter;

public enum Title {
    MANAGE_CATEGORIES("Управление категориями"),
    KEYWORDS("Ключевые слова"),
    ADD_CATEGORY(Character.toString('➕')),
    EDIT_NAME("Изменить название"),
    DELETE_CATEGORY("Удалить категорию"),
    BACK_TO_CATEGORIES("Назад");
    @Getter
    private final String text;

    Title(String text) {
        this.text = text;
    }
}
