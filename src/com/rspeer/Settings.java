package com.rspeer;

import com.rspeer.settings.DropType;
import com.rspeer.settings.Speed;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.movement.position.Position;

import java.util.HashSet;

public class Settings {

    private static boolean isSetup;
    private static DropType dropType;
    private static Speed speed;
    private static HashSet<Position> selectedRocks;
    private static Position workTile;
    private static int totalRocksSelected;
    private static int runEnergyToggle;

    static {
        selectedRocks = new HashSet<>();
    }

    public static HashSet<Position> getSelectedRocks() {
        return selectedRocks;
    }

    public static void appendSelectedRock(Position position) {
        Settings.selectedRocks.add(position);
    }

    public static void removeSelectedRock(Position position) {
        Settings.selectedRocks.remove(position);
    }

    public static DropType getDropType() {
        return dropType;
    }

    public static void setDropType(DropType dropType) {
        Settings.dropType = dropType;
    }

    public static Speed getSpeed() {
        return speed;
    }

    public static void setSpeed(Speed speed) {
        Settings.speed = speed;
    }

    public static Position getWorkTile() {
        return workTile;
    }

    public static void setWorkTile(Position workTile) {
        Settings.workTile = workTile;
    }

    public static int getLoopReturn() {
        if(speed == null) {
            return  Random.nextInt(250, 450);
        }
        switch (speed) {
            case SLOW:
                return Random.nextInt(550, 750);
            case MEDIUM:
                return Random.nextInt(250, 450);
            case FAST:
                return Random.nextInt(50, 150);
            case INSANE:
                return Random.nextInt(10, 50);
            default:
                return Random.nextInt(250, 450);
        }
    }

    public static boolean isSetup() {
        return isSetup;
    }

    public static void setIsSetup(boolean isSetup) {
        Settings.isSetup = isSetup;
    }

    public static int getTotalRocksSelected() {
        return totalRocksSelected;
    }

    public static void setTotalRocksSelected(int totalRocksSelected) {
        Settings.totalRocksSelected = totalRocksSelected;
    }

    public static int getRunEnergyToggle() {
        if(runEnergyToggle == 0) {
            runEnergyToggle = Random.nextInt(45, 65);
        }
        return runEnergyToggle;
    }

    public static void setRunEnergyToggle(int runEnergyToggle) {
        Settings.runEnergyToggle = runEnergyToggle;
    }
}
