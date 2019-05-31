package org.ploxie.pathfinder.collision;

import org.ploxie.pathfinder.wrapper.Position;
import org.rspeer.runetek.adapter.scene.SceneObject;

import java.util.function.Predicate;

public class DoorPredicate implements Predicate<SceneObject> {

    private Position position;

    public DoorPredicate(Position position){
        this.position = position;
    }

    @Override
    public boolean test(SceneObject sceneObject) {
        if(sceneObject.getPosition().getX() != position.getX() || sceneObject.getPosition().getY() != position.getY() || sceneObject.getPosition().getFloorLevel() != position.getZ()){
            return false;
        }

        if(sceneObject.getName().toLowerCase().contains("door")){
            return true;
        }
        return false;
    }

}
