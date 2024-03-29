package org.ploxie.pathfinder.wrapper;

import org.ploxie.pathfinder.Walker;
import org.ploxie.pathfinder.Walker2;
import org.ploxie.pathfinder.collision.DefaultReachable;
import org.ploxie.pathfinder.methods.astar.AStar;
import org.ploxie.pathfinder.web.Web;
import org.ploxie.pathfinder.web.connections.*;
import org.ploxie.pathfinder.web.node.Node;
import org.ploxie.pathfinder.web.node.TileNode;
import org.ploxie.pathfinder.web.path.LocalPath;
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
        Path path = findLocalPath(start, end);
        if(path != null){
            return path;
        }

        return findWebPath(start, end);
    }

    @Override
    public LocalPath findLocalPath(Position start, Position end){
        boolean canReach = Walker.getInstance().getReachable().canReach(end, start);
        if (!canReach) {
            return null;
        }

        return new AStar().buildPath(new TileNode(start), new TileNode(end));
    }

    @Override
    public WebPath findWebPath(Position start, Position end){
        return new AStar().buildPath(web.getNearestNode(start), web.getNearestNode(end));
    }

    @Override
    public boolean execute(Path path) {
        if (path instanceof LocalPath) {
            return execute((LocalPath) path);
        }
        if (path instanceof WebPath) {
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

    private boolean execute(LocalPath path) {
        NodeConnection firstAction = path.getFirstActionConnection();
        if (firstAction != null) {
            return executeConnection(firstAction);
        }

        NodeConnection walkConnection = path.getLastWalkConnection(Walker.getInstance().getReachable());
        if (walkConnection != null) {
            return executeConnection(walkConnection);
        }

        return true;
    }

    private boolean execute(WebPath path) {
        NodeConnection firstAction = path.getFirstActionConnection();
        if (firstAction != null && Walker.getInstance().getReachable().canReach(firstAction.getSource().getPosition(), Walker2.getLocalPlayerPosition())) {
            LocalPath localPath = new AStar().buildPath(new TileNode(Walker2.getLocalPlayerPosition()), new TileNode(firstAction.getSource().getPosition()));
            if (localPath == null || !localPath.containsSpecialAction()) {
                Log.info("executing special connection: " + firstAction.getType());
                return executeConnection(firstAction);
            } else {
                execute(localPath);
            }
        }

        NodeConnection walkConnection = path.getLastWalkConnection(Walker.getInstance().getReachable());
        if (walkConnection != null) {
            LocalPath localPath = new AStar().buildPath(new TileNode(Walker2.getLocalPlayerPosition()), new TileNode(walkConnection.getTarget().getPosition()));
            if (localPath == null) {
                Position nearestPosition = Walker.getInstance().getReachable().getClosestTo(walkConnection.getTarget());
                localPath = new AStar().buildPath(new TileNode(Walker2.getLocalPlayerPosition()), new TileNode(nearestPosition));
            }
            if (localPath != null) {
                if (!localPath.containsSpecialAction()) {
                    return executeConnection(walkConnection);
                } else {
                    execute(localPath);
                }
            }
        }

        Log.info("WEBPATH CANT TRAVERSE");
        return false;
    }


}
