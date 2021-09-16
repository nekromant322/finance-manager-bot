package com.nekromant.finance.contants;

public enum CallBack {
    APPROVE("/approve"),
    DENY("/deny");

    private String alias;

    CallBack(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }
}
