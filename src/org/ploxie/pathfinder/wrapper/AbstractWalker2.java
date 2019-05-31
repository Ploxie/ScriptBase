package org.ploxie.pathfinder.wrapper;


import org.ploxie.pathfinder.collision.LocalRegion;
import org.ploxie.pathfinder.collision.ReachMap2;
import org.ploxie.pathfinder.collision.Region;
import org.ploxie.pathfinder.methods.AStarPathfinder;
import org.ploxie.pathfinder.methods.LocalPathfinder;
import org.ploxie.pathfinder.methods.Pathfinder2;
import org.ploxie.pathfinder.web.Web;
import org.ploxie.pathfinder.web.connections.WalkConnection2;
import org.ploxie.pathfinder.web.connections.WebNodeConnection;
import org.ploxie.pathfinder.web.path.LocalPath;
import org.ploxie.pathfinder.web.WebNode;
import org.ploxie.pathfinder.web.path.WebPath;
import org.rspeer.ui.Log;

public abstract class AbstractWalker2 {

    protected Web web;

    public AbstractWalker2(Web web) {
        this.web = web;
    }

    public abstract Position getPlayerPosition();

    public abstract Region getLocalRegion();

    public abstract void walkTo(Position target);

    public WebPath findLocalPath(Position end){
        return findLocalPath(getPlayerPosition(), end);
    }

    public WebPath findLocalPath(Position start, Position end){
        /*Region localRegion = getLocalRegion();
        Pathfinder2 localPathfinder = new LocalPathfinder(localRegion);
        WebNode startNode = new WebNode(start.getX(),start.getY(),start.getZ());
        WebNode endNode = new WebNode(end.getX(),end.getY(),end.getZ());
        return localPathfinder.findPath(startNode, endNode);*/
        return null;
    }

    public WebPath findPath(Position end){
        return findPath(getPlayerPosition(), end);
    }

    public void traversePath(WebPath path) {
        if (path instanceof LocalPath) {
            path.traverse();
            return;
        }

        WebNode current = path.getFirst();
        WebNode lastWalkable = null;//path.getLastWalkableNode();
        WebNode next = path.getNext(lastWalkable);

        WebNodeConnection toNext = lastWalkable.getConnection(path.getNext(lastWalkable));

        boolean isOnLast = current.equals(lastWalkable);
        boolean nextIsAction = isOnLast && toNext != null && !(toNext instanceof WalkConnection2);

        if(nextIsAction){
            //HANDLE ACTION
            Log.info("Handle action");
            return;
        }

        WebPath localPath = null;
            Position closestToNext = ReachMap2.getClosestTo(next);
            localPath = findLocalPath(closestToNext);


        if(localPath != null){
            traversePath(localPath);
        }

    }

    public WebPath findPath(Position start, Position end) {
        /*WebNode closestStartNode = web.getNearestNode(start);

        if(!ReachMap2.canReach(closestStartNode)){
            Log.severe("Can't find start node");
            return null;
        }

        WebNode closestEndNode = web.getNearestNode(end);

        if(closestStartNode.equals(closestEndNode)){
            if(!ReachMap2.canReach(end)){
                Log.severe("No web node close enough to goal");
                return null;
            }

            Log.info("Walking tail");
            return new LocalPathfinder(getLocalRegion()).findPath(new WebNode(start), new WebNode(end));
        }*/

        //return new AStarPathfinder().findPath(closestStartNode, closestEndNode);
        return null;
    }

}
