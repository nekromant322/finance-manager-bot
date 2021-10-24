package com.nekromant.finance.contants;

public enum CallBackData {
    GET_CATEGORY_INFO("!get_category_info");
    private String alias;

    CallBackData(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }
}
