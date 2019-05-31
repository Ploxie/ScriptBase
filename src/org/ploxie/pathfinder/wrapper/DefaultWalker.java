package org.ploxie.pathfinder.wrapper;

import org.ploxie.pathfinder.Walker;
import org.ploxie.pathfinder.Walker2;
import org.ploxie.pathfinder.collision.DefaultReachable;
import org.ploxie.pathfinder.methods.astar.AStar;
import org.ploxie.pathfinder.web.Web;
import org.ploxie.pathfinder.web.connections.*;
import org.ploxie.pathfinder.web.connections.executor.NodeConnectionExecutor;
import org.ploxie.pathfinder.web.node.Node;
import org.ploxie.pathfinder.web.node.TileNode;
import org.ploxie.pathfinder.web.path.LocalPath;
import org.ploxie.pathfinder.web.path.NodePath;
import org.ploxie.pathfinder.web.path.Path;
import org.ploxie.pathfinder.web.path.WebPath;
import org.ploxie.wrapper.Position;
import org.rspeer.ui.Log;

public class DefaultWalker extends Walker {

    public DefaultWalker(Web web) {
        super(web);
        setReachable(new DefaultReachable());
    }

    @Override
    public Path findPath(Position start, Position end) {
        Node startNode = web.getNearestNode(start);
        Node endNode = web.getNearestNode(end);


        boolean canReach = Walker.getInstance().getReachable().canReach(end, start);
        if (canReach) {
            startNode = new TileNode(start);
            endNode = new TileNode(end);
        }

        return new AStar().buildPath(startNode, endNode);
    }

    @Override
    public boolean execute(Path path) {
        if(path instanceof LocalPath){
            return execute((LocalPath)path);
        }
        if(path instanceof WebPath){
            return execute((WebPath) path);
        }

        Log.severe("CANT EXECUTE PATH");

        return false;

        /*NodeConnection firstConnection = path.getConnections().get(0);
        if (firstConnection == null) {
            return true;
        }

        NodeConnection lastConnection = firstConnection;
        if (firstConnection instanceof WalkConnection) {
            for (NodeConnection connection : path.getConnections()) {
                if (!(connection instanceof WalkConnection)) {
                    break;
                }

                if (!Walker.getInstance().getReachable().canReach(connection.getTarget(), connection.getSource())) {
                    break;
                }

                lastConnection = connection;
            }
        }

        NodeConnectionExecutor executor = getConnectionExecutor(lastConnection);
        if (executor != null) {
            return executor.execute(lastConnection);
        }

        return false;*/
    }

    private boolean execute(LocalPath path){
        NodeConnection firstAction = path.getFirstActionConnection();
        if(firstAction != null){
            return executeConnection(firstAction);
        }

        NodeConnection walkConnection = path.getLastWalkConnection(Walker.getInstance().getReachable());
        if(walkConnection != null){
            return executeConnection(walkConnection);
        }
        return false;
    }

    private boolean execute(WebPath path){
        NodeConnection firstAction = path.getFirstActionConnection();
        if(firstAction != null && Walker.getInstance().getReachable().canReach(firstAction.getSource().getPosition(), Walker2.getLocalPlayerPosition())){
            LocalPath localPath = new AStar().buildPath(new TileNode(Walker2.getLocalPlayerPosition()), new TileNode(firstAction.getSource().getPosition()));
            if(!localPath.containsSpecialAction()){
                return executeConnection(firstAction);
            }else{
                execute(localPath);
            }
        }

        NodeConnection walkConnection = path.getLastWalkConnection(Walker.getInstance().getReachable());
        if(walkConnection != null){
            LocalPath localPath = new AStar().buildPath(new TileNode(Walker2.getLocalPlayerPosition()), new TileNode(walkConnection.getTarget().getPosition()));
            if(!localPath.containsSpecialAction()){
                return executeConnection(walkConnection);
            }else{
                execute(localPath);
            }
        }
        return false;
    }


}
