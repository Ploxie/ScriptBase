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
import org.ploxie.pathfinder.web.path.Path;
import org.ploxie.wrapper.Position;

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
        if(canReach){
            startNode = new TileNode(start);
            endNode = new TileNode(end);
        }

        return new AStar().buildPath(startNode, endNode);
    }

    @Override
    public boolean execute(Path path) {
        NodeConnection firstConnection = path.getConnections().get(0);
        if(firstConnection == null){
            return true;
        }

        NodeConnection lastConnection = firstConnection;
        if(firstConnection instanceof WalkConnection){
            for (NodeConnection connection : path.getConnections()) {
                if (!(connection instanceof WalkConnection)) {
                    if(Walker.getInstance().getReachable().canReach(connection.getSource(), Walker2.getLocalPlayerPosition())){
                        lastConnection = connection;
                    }
                    break;
                }

                if(!Walker.getInstance().getReachable().canReach(connection.getTarget(), connection.getSource())){
                    break;
                }

                lastConnection = connection;
            }
        }

        NodeConnectionExecutor executor = getConnectionExecutor(lastConnection);
        if(executor != null){
            return executor.execute(lastConnection);
        }

        return false;
    }


}
