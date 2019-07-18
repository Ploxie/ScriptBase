package com.rspeer;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.path.Path;
import org.rspeer.runetek.api.movement.position.Position;

public class PathHelper {

    public static Path tryGetPath(Position tile, int tries) {
        Path p = Movement.buildPath(tile);
        int attempts = 0;
        while (p == null) {
            if(attempts > tries) {
                break;
            }
            p = Movement.buildPath(tile);
            if(p != null) {
                break;
            }
            attempts++;
            Time.sleep(500, 1000);
        }
        return p;
    }


}
