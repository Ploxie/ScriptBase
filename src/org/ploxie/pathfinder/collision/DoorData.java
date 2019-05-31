package org.ploxie.pathfinder.collision;

import org.ploxie.wrapper.Position;

public class DoorData {

    private String name;
    private String openAction;
    private Position position;

    public static final String[] DOOR_NAMES = new String[]{"Door"};
    public static final String[] DOOR_ACTIONS = new String[]{"Open"};

    public DoorData(Position position, String name, String openAction) {
        this.name = name;
        this.openAction = openAction;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public String getOpenAction() {
        return openAction;
    }

    public Position getPosition() {
        return position;
    }

    public static boolean isDoor(String doorName){
        for(String name : DOOR_NAMES){
            if(name.equalsIgnoreCase(doorName)){
                return true;
            }
        }

        return false;
    }

    public static boolean isDoorAction(String doorAction){
        for(String name : DOOR_ACTIONS){
            if(name.equalsIgnoreCase(doorAction)){
                return true;
            }
        }

        return false;
    }

}
