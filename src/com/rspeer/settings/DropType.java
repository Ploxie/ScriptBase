package com.rspeer.settings;

public enum DropType {

    WHEN_FULL,
    M1D1,
    M2D2,
    BANK;

    public static DropType fromText(String text) {
        for (DropType value : values()) {
            String compare = value.name().toLowerCase().trim().replace("_", " ");
            if(compare.equals(text.toLowerCase().trim()))
                return value;
        }
        return null;
    }

}
