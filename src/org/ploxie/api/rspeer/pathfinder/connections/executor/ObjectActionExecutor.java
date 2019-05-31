package org.ploxie.api.rspeer.pathfinder.connections.executor;

import org.ploxie.pathfinder.Walker2;
import org.ploxie.pathfinder.methods.astar.AStar;
import org.ploxie.pathfinder.web.connections.ObjectConnection;
import org.ploxie.pathfinder.web.connections.executor.NodeConnectionExecutor;
import org.ploxie.pathfinder.web.node.TileNode;
import org.ploxie.pathfinder.web.path.LocalPath;
import org.ploxie.wrapper.Position;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.ui.Log;

public class ObjectActionExecutor implements NodeConnectionExecutor<ObjectConnection> {
    @Override
    public boolean execute(ObjectConnection connection) {
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
            return object.interact(connection.getInteractionString());
        }else{
            Log.info("Object is null "+connection.getPosition());
        }
        return false;
    }

    @Override
    public Class<ObjectConnection> getType() {
        return ObjectConnection.class;
    }
}
