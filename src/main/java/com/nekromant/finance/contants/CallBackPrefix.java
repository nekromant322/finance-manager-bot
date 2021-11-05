package com.nekromant.finance.contants;

public enum CallBackPrefix {
    GET_CATEGORY_INFO("!get_category_info"),
    GET_KEYWORDS("!get_keywords_info"),
    EDIT_NAME_CATEGORY("!edit_name"),
    ADD_CATEGORY("!add_category"),
    DELETE_CATEGORY("!delete_category");

    private String alias;

    CallBackPrefix(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }
}
