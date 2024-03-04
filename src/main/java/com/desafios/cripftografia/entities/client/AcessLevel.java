package com.desafios.cripftografia.entities.client;

public enum AcessLevel {
    PREMIUM("premium"),
    COMMON("common");

    private String Level;

    AcessLevel(String level) {
        this.Level = level;
    }

    public String getLevel() {
        return Level;
    }
}
