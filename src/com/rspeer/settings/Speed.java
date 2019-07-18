package com.rspeer.settings;

public enum Speed {

    SLOW,
    MEDIUM,
    FAST,
    INSANE;

    public static Speed fromText(String text) {
        for (Speed value : values()) {
            String compare = value.name().toLowerCase().trim().replace("_", " ");
            if(compare.equals(text.toLowerCase().trim()))
                return value;
        }
        return null;
    }

}
