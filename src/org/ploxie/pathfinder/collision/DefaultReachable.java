package org.ploxie.pathfinder.collision;

import org.ploxie.pathfinder.Walker2;
import org.ploxie.pathfinder.methods.astar.AStar;
import org.ploxie.pathfinder.web.node.TileNode;
import org.ploxie.pathfinder.wrapper.Direction;
import org.ploxie.pathfinder.wrapper.Position;
import org.rspeer.ui.Log;

import java.util.HashSet;
import java.util.Iterator;

public class DefaultReachable implements Reachable {

    protected HashSet<TileNode> reachMap = new HashSet<>();

    protected void reload(TileNode start){
        reachMap.clear();
        searchTile(start);
    }

    protected void searchTile(TileNode tile){
        for(Direction direction : Direction.values()){
            searchNeighbour(tile, direction);
        }
    }

    protected void searchNeighbour(TileNode current, Direction direction){
        TileNode target = current.translate(direction);
        Region localRegion = Walker2.getLocalRegion();

        if(reachMap.contains(target)){
            return;
        }
        if(!localRegion.canGoInDirection(current.getPosition(), direction)){
            return;
        }

        reachMap.add(target);
        searchTile(target);
    }

    public boolean canReach(Position position, Position from){
        return new AStar().buildPath(new TileNode(from), new TileNode(position)) != null;
    }

    public Position getClosestTo(Position position){
        reload(new TileNode(Walker2.getLocalPlayerPosition()));
        if(reachMap.contains(position)){
            return position;
        }

        Position closest = null;
        int distance = Integer.MAX_VALUE;
        for(TileNode node : reachMap){

            Position pos = node.getPosition();
            int dist = position.distanceTo(pos);
            if(dist < distance){

                closest = pos;
                distance = dist;
            }
        }

        return closest;
    }

}
