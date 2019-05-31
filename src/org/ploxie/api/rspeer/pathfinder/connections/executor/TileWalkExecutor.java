package org.ploxie.api.rspeer.pathfinder.connections.executor;

import org.ploxie.pathfinder.web.connections.TileWalkConnection;
import org.ploxie.pathfinder.web.connections.executor.NodeConnectionExecutor;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;

public class TileWalkExecutor implements NodeConnectionExecutor<TileWalkConnection> {
    @Override
    public boolean execute(TileWalkConnection connection) {
        Movement.setWalkFlag(new Position(connection.getTarget().getX(), connection.getTarget().getY(), connection.getTarget().getZ()));
        return true;
    }

    @Override
    public Class<TileWalkConnection> getType() {
        return TileWalkConnection.class;
    }
}
