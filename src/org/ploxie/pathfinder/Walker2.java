package org.ploxie.pathfinder;

import org.ploxie.pathfinder.collision.LocalRegion;
import org.ploxie.pathfinder.collision.Region;
import org.ploxie.pathfinder.methods.astar.AStar;
import org.ploxie.pathfinder.web.node.TileNode;
import org.ploxie.pathfinder.web.path.WebPath;
import org.ploxie.pathfinder.wrapper.AbstractWalker2;
import org.ploxie.pathfinder.wrapper.Position;

public class Walker2 {

    private static AbstractWalker2 internalWalker;

    public static Position getLocalPlayerPosition(){
        return internalWalker.getPlayerPosition();
    }

    public static void walkTo(Position position){
        internalWalker.walkTo(position);
    }

    public static Region getLocalRegion(){
        return internalWalker.getLocalRegion();
    }

    public static void setInternalWalker(AbstractWalker2 walker){
        internalWalker = walker;
    }

    public static WebPath findLocalPath(Position end) {
        return internalWalker.findLocalPath(end);
    }

    public static WebPath findLocalPath(Position start, Position end) {
        return internalWalker.findLocalPath(start, end);
    }

    public static WebPath findPath(Position end) {
        return internalWalker.findPath(end);
    }

    public static WebPath findPath(Position start, Position end){
        return internalWalker.findPath(start, end);
    }

    public static void traversePath(WebPath path){
        internalWalker.traversePath(path);
    }

    public static boolean canReach(Position target){
        return new AStar().buildPath(new TileNode(getLocalPlayerPosition()), new TileNode(target)) != null;
    }
}
