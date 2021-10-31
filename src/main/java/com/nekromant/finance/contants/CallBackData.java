package com.nekromant.finance.contants;

public enum CallBackData {
    GET_CATEGORY_INFO("!get_category_info"),
    GET_KEYWORDS("!get_keywords_info"),
    EDIT_NAME_CATEGORY("!edit_name"),
    DELETE_CATEGORY("!delete_category");
    private String alias;

    CallBackData(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }
}
