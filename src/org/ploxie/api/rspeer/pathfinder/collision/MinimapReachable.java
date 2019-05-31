package org.ploxie.api.rspeer.pathfinder.collision;

import org.ploxie.pathfinder.Walker2;
import org.ploxie.pathfinder.collision.DefaultReachable;
import org.ploxie.pathfinder.collision.Reachable;
import org.ploxie.pathfinder.collision.Region;
import org.ploxie.pathfinder.methods.astar.AStar;
import org.ploxie.pathfinder.web.connections.NodeConnection;
import org.ploxie.pathfinder.web.node.TileNode;
import org.ploxie.pathfinder.web.path.Path;
import org.ploxie.pathfinder.wrapper.DefaultWalker;
import org.ploxie.pathfinder.wrapper.Direction;
import org.ploxie.pathfinder.wrapper.Position;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.Projection;
import org.rspeer.ui.Log;

import java.awt.*;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MinimapReachable extends DefaultReachable {
    @Override
    public boolean canReach(Position target, Position from) {
        Path localPath = new AStar().buildPath(new TileNode(from), new TileNode(target));

        if(localPath == null){
            return false;
        }

        List<NodeConnection> connections = localPath.getConnections();
        Collections.reverse(connections);

        Random random = new Random(Walker2.getLocalPlayerPosition().hashCode());
        int distance = random.nextInt(30) + 40;

        for(NodeConnection c : connections){
            org.rspeer.runetek.api.movement.position.Position walkPosition = new org.rspeer.runetek.api.movement.position.Position(c.getTarget().getX(), c.getTarget().getY(), c.getTarget().getZ());
            Point p = Projection.toMinimap(walkPosition);
            Point minimapCenter = Projection.toMinimap(Players.getLocal().getPosition());
            if(p != null && p.distance(minimapCenter) <= distance){
                return true;
            }
        }

        return false;
    }

    @Override
    protected void searchNeighbour(TileNode current, Direction direction){
        TileNode target = current.translate(direction);
        Region localRegion = Walker2.getLocalRegion();

        Point minimapPoint = Projection.toMinimap(new org.rspeer.runetek.api.movement.position.Position(target.getX(), target.getY(),target.getZ()));

        if(minimapPoint == null){
            return;
        }

        if(reachMap.contains(target)){
            return;
        }
        if(!localRegion.canGoInDirection(current.getPosition(), direction)){
            return;
        }

        reachMap.add(target);
        searchTile(target);
    }
}
