package com.rspeer;

import org.rspeer.runetek.api.movement.position.Position;

public class Store {

    private static Position currentRock;

    public static Position getCurrentRock() {
        return currentRock;
    }

    public static void setCurrentRock(Position currentRock) {
        Store.currentRock = currentRock;
    }


}
