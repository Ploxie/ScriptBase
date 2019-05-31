package org.ploxie.pathfinder.methods;

import org.ploxie.pathfinder.Walker2;
import org.ploxie.pathfinder.collision.LocalRegion;
import org.ploxie.pathfinder.collision.ReachMap2;
import org.ploxie.pathfinder.collision.Region;
import org.ploxie.pathfinder.wrapper.Position;

public class FloodFill {

    public static ReachMap2 execute(Position start){
        ReachMap2 map = new ReachMap2();
        Region region = Walker2.getLocalRegion();

        addTile(map, region, start);

        return map;
    }

    private static void addTile(ReachMap2 map, Region region, Position tile){
        addNeighbour(map, region, tile, -1, 1);
        addNeighbour(map, region, tile, 0, 1);
        addNeighbour(map, region, tile, 1, 1);
        addNeighbour(map, region, tile, 1, 0);
        addNeighbour(map, region, tile, 1, -1);
        addNeighbour(map, region, tile, 0, -1);
        addNeighbour(map, region, tile, -1, -1);
        addNeighbour(map, region, tile, -1, 0);
    }

    private static void addNeighbour(ReachMap2 map, Region region, Position current, int xDirection, int yDirection){
        Position target = current.translate(xDirection, yDirection, current.getZ());

        if(map.contains(target)){
            return;
        }
        if(!region.canGoInDirection(current, xDirection, yDirection)){
            return;
        }
        map.add(target);
        addTile(map, region, target);
    }

}
