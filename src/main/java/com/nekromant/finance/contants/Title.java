package com.nekromant.finance.contants;

import lombok.Getter;

public enum Title {
    MANAGE_CATEGORIES("Управление категориями"),
    KEYWORDS("Ключевые слова"),
    ADD_CATEGORY(Character.toString(0x00002795)),
    EDIT_NAME("Изменить название"),
    DELETE_CATEGORY("Удалить категорию"),
    EDIT_KEYWORDS("Редактировать ключевые слова"),
    BACK("Назад");
    @Getter
    private final String text;

    Title(String text) {
        this.text = text;
    }
}
