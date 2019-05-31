package org.ploxie.pathfinder.collision;

import org.ploxie.pathfinder.Walker2;
import org.ploxie.pathfinder.methods.FloodFill;
import org.ploxie.pathfinder.wrapper.Position;

import java.util.HashSet;
import java.util.Iterator;

public class ReachMap2 extends HashSet<Position> {

    private static ReachMap2 reachMap;

    public static ReachMap2 getReachMap(){
        return reachMap;
    }

    public static void forceReload(){
        reachMap = FloodFill.execute(Walker2.getLocalPlayerPosition());
    }

    public static boolean canReach(Position position){
        if(!reachMap.contains(position)){
            return false;
        }
        return true;
    }

    public static Position getClosestTo(Position position){
        if(reachMap.contains(position)){
            return position;
        }

        Position closest = null;
        int distance = Integer.MAX_VALUE;
        for(Iterator<Position> iterator = reachMap.iterator(); iterator.hasNext();){

            Position pos = iterator.next();
            int dist = position.distanceTo(pos);
            if(dist < distance){

                closest = pos;
                distance = dist;
            }
        }

        return closest;
    }

}
