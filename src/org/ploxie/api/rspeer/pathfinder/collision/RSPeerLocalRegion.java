package org.ploxie.api.rspeer.pathfinder.collision;

import org.ploxie.pathfinder.collision.DoorData;
import org.ploxie.pathfinder.collision.region.Region;
import org.ploxie.wrapper.Position;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.scene.SceneObjects;

public class RSPeerLocalRegion extends Region {

    public RSPeerLocalRegion(Position base, int[][] collisionData) {
        super(base, collisionData);
    }

    @Override
    public DoorData getDoorData(Position position) {
        SceneObject door = SceneObjects.getFirstAt(new org.rspeer.runetek.api.movement.position.Position(position.getX(), position.getY(),position.getZ()));

        if(door == null){
            return null;
        }

        if(!DoorData.isDoor(door.getName())){
            return null;
        }

        String action = null;
        for(String a : door.getActions()){
            if(DoorData.isDoorAction(a)){
                action = a;
                break;
            }
        }

        return new DoorData(position, door.getName(), action);
    }


}
