package com.rspeer;

public class Stats {

    private static int oreMined;
    private static int minutesRunTime;

    public static int getOreMined() {
        return oreMined;
    }

    public static void setOreMined(int oreMined) {
        Stats.oreMined = oreMined;
    }

    public static int getMinuteRunTime() {
        return minutesRunTime;
    }

    public static void incrementRunTime() {
       minutesRunTime++;
    }
}
