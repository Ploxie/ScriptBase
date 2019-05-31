package org.ploxie.api.rspeer.pathfinder.connections.executor;

import org.ploxie.pathfinder.Walker;
import org.ploxie.pathfinder.Walker2;
import org.ploxie.pathfinder.methods.astar.AStar;
import org.ploxie.pathfinder.web.connections.NodeConnection;
import org.ploxie.pathfinder.web.connections.NodeWalkConnection;
import org.ploxie.pathfinder.web.connections.WalkConnection;
import org.ploxie.pathfinder.web.connections.executor.NodeConnectionExecutor;
import org.ploxie.pathfinder.web.node.TileNode;
import org.ploxie.pathfinder.web.path.Path;
import org.ploxie.wrapper.Position;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.Projection;
import org.rspeer.ui.Log;

import java.awt.*;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class NodeWalkExecutor implements NodeConnectionExecutor<NodeWalkConnection> {
    @Override
    public boolean execute(NodeWalkConnection connection) {

        Position walkToPosition = connection.getTarget().getPosition();
        if(!Walker.getInstance().getReachable().canReach(walkToPosition, Walker2.getLocalPlayerPosition())){
            walkToPosition = Walker.getInstance().getReachable().getClosestTo(walkToPosition);
        }

        Path localPath = new AStar().buildPath(new TileNode(Walker2.getLocalPlayerPosition()), new TileNode(walkToPosition));

        List<NodeConnection> connections = localPath.getConnections();
        Collections.reverse(connections);

        Random random = new Random(Walker2.getLocalPlayerPosition().hashCode());
        int distance = random.nextInt(30) + 40;

        for(NodeConnection c : connections){
            org.rspeer.runetek.api.movement.position.Position walkPosition = new org.rspeer.runetek.api.movement.position.Position(c.getTarget().getX(), c.getTarget().getY(), c.getTarget().getZ());
            Point p = Projection.toMinimap(walkPosition, false);
            Point minimapCenter = Projection.toMinimap(Players.getLocal().getPosition());
            if(p != null && p.distance(minimapCenter) <= distance){
                walkToPosition = c.getTarget().getPosition();
                break;
            }
        }

        Movement.setWalkFlag(new org.rspeer.runetek.api.movement.position.Position(walkToPosition.getX(), walkToPosition.getY(), walkToPosition.getZ()));
        return true;
    }

    @Override
    public Class<NodeWalkConnection> getType() {
        return NodeWalkConnection.class;
    }
}
