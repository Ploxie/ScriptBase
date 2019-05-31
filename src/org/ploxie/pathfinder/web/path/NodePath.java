package org.ploxie.pathfinder.web.path;

import org.ploxie.pathfinder.collision.Reachable;
import org.ploxie.pathfinder.methods.PathExecutor;
import org.ploxie.pathfinder.web.connections.NodeConnection;
import org.ploxie.pathfinder.web.connections.WalkConnection;
import org.ploxie.pathfinder.web.node.Node;

import java.util.List;

public class NodePath implements Path {

    private Node startNode;
    private Node endNode;
    private List<NodeConnection> connections;

    public NodePath(Node start, Node end, List<NodeConnection> connections){
        this.connections = connections;
        this.startNode = start;
        this.endNode = end;
    }

    @Override
    public Node getStartNode(){
        return startNode;
    }

    @Override
    public Node getEndNode(){
        return endNode;
    }

    @Override
    public List<NodeConnection> getConnections(){
        return connections;
    }

    @Override
    public boolean traverse(PathExecutor executor) {
        return executor.execute(this);
    }

    public boolean containsSpecialAction(){
        return getFirstActionConnection() != null;
    }

    public NodeConnection getFirstActionConnection(){
        for(NodeConnection connection : connections){
            if(!(connection instanceof WalkConnection)){
                return connection;
            }
        }

        return null;
    }

    public NodeConnection getLastWalkConnection(Reachable reachable){
        if(connections.size() == 0){
            return null;
        }
        NodeConnection firstConnection = connections.get(0);
        NodeConnection lastConnection = firstConnection;
        for(NodeConnection connection : connections){
            if(!(connection instanceof WalkConnection)){
                break;
            }

            if(!reachable.canReach(connection.getTarget(), firstConnection.getSource())){
                break;
            }

            lastConnection = connection;
        }
        return lastConnection;
    }

}
