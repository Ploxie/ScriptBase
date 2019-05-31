package org.ploxie.api.rspeer.pathfinder.connections.executor;

import org.ploxie.pathfinder.web.connections.ObjectActionConnection;
import org.ploxie.pathfinder.web.connections.executor.NodeConnectionExecutor;
import org.ploxie.wrapper.Position;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.ui.Log;

public class ObjectActionExecutor implements NodeConnectionExecutor<ObjectActionConnection> {
    @Override
    public boolean execute(ObjectActionConnection connection) {
        SceneObject object = SceneObjects.getBest((o1, o2) -> {
            Position pos = new Position(o1.getX(), o1.getY(), o1.getFloorLevel());
            Position otherPos = new Position(o2.getX(), o2.getY(), o2.getFloorLevel());
            return pos.distanceTo(otherPos);
        }, sceneObject -> {
            if (connection.getPosition() != null) {
                if (sceneObject.getPosition().getX() != connection.getPosition().getX() || sceneObject.getPosition().getY() != connection.getPosition().getY() || sceneObject.getPosition().getFloorLevel() != connection.getPosition().getZ()) {
                    return false;
                }
            }
            if (!sceneObject.getName().equalsIgnoreCase(connection.getObjectName())) {
                return false;
            }
            for (String action : sceneObject.getActions()) {
                if (action.equalsIgnoreCase(action)) {
                    return true;
                }
            }
            return false;
        });



        if(object != null){
            Log.info("Object: "+object.getPosition());
            return object.interact(connection.getInteractionString());
        }
        return false;
    }

    @Override
    public Class<ObjectActionConnection> getType() {
        return ObjectActionConnection.class;
    }
}
