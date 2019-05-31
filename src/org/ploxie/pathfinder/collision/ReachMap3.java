package org.ploxie.pathfinder.collision;

import org.ploxie.pathfinder.Walker2;
import org.ploxie.pathfinder.methods.astar.AStar;
import org.ploxie.pathfinder.web.node.TileNode;
import org.ploxie.pathfinder.wrapper.Direction;
import org.ploxie.pathfinder.wrapper.Position;
import org.rspeer.ui.Log;

import java.util.HashSet;
import java.util.Iterator;

public class ReachMap3 {

    private static HashSet<TileNode> reachMap = new HashSet<>();

    private static void reload(TileNode start){
        Log.info("Reloading");
        reachMap.clear();

        searchTile(start);
    }

    private static void searchTile(TileNode tile){
        for(Direction direction : Direction.values()){
            searchNeighbour(tile, direction);
        }
    }

    private static boolean needToUpdate(){
        Position playerPos = Walker2.getLocalPlayerPosition();
        if(!reachMap.contains(playerPos)){
            return true;
        }
        return false;
    }

    private static void searchNeighbour(TileNode current, Direction direction){
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

    public static boolean canReach(Position position, Position from){
        /*reload(new TileNode(Walker2.getLocalPlayerPosition()));

        TileNode start = new TileNode(from);
        if(!reachMap.contains(start)){
            reload(start);
        }

        return reachMap.contains(new TileNode(position));*/
        return new AStar().buildPath(new TileNode(from), new TileNode(position)) != null;
    }

    public static Position getClosestTo(Position position){
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
