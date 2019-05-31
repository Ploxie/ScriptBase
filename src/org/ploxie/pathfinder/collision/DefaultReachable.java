package org.ploxie.pathfinder.collision;

import org.ploxie.pathfinder.Walker2;
import org.ploxie.pathfinder.collision.region.Region;
import org.ploxie.pathfinder.methods.astar.AStar;
import org.ploxie.pathfinder.web.node.TileNode;
import org.ploxie.wrapper.Direction;
import org.ploxie.wrapper.Position;
import org.ploxie.wrapper.Positionable;

import java.util.HashSet;

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

    public boolean canReach(Positionable position, Positionable from){
        return new AStar().buildPath(new TileNode(from.getPosition()), new TileNode(position.getPosition())) != null;
    }

    public Position getClosestTo(Positionable positionable){
        reload(new TileNode(Walker2.getLocalPlayerPosition()));
        if(reachMap.contains(positionable)){
            return positionable.getPosition();
        }

        Position closest = null;
        int distance = Integer.MAX_VALUE;
        for(TileNode node : reachMap){

            Position pos = node.getPosition();
            int dist = positionable.distanceTo(pos);
            if(dist < distance){

                closest = pos;
                distance = dist;
            }
        }

        return closest;
    }

}
