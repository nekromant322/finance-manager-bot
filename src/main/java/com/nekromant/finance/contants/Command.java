package com.nekromant.finance.contants;

public enum Command {

    REGISTER_MENTOR("register_mentor", "Зарегистрировать чат как менторский");


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
