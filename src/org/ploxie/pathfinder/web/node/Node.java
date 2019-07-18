package org.ploxie.pathfinder.web.node;

import org.ploxie.pathfinder.web.connections.NodeConnection;
import org.ploxie.pathfinder.web.connections.NodeWalkConnection;
import org.ploxie.wrapper.Position;
import org.ploxie.wrapper.Positionable;

import java.util.HashSet;
import java.util.Set;

public abstract class Node implements Positionable {

    protected Set<NodeConnection> connectionSet = new HashSet<>();
    protected Position position;

    public Node(int x, int y, int z) {
        this.position = new Position(x,y,z);
    }

    public Node(Position position) {
        this.position = position;
    }

    public Position getPosition(){
        return position;
    }

    public boolean addConnection(Node node) {
        NodeConnection connection = new NodeWalkConnection(this, node);
        if (connectionSet.contains(connection)) {
            return false;
        }
        return addConnection(connection);
    }

    public boolean addConnection(NodeConnection connection) {
        return !connectionSet.contains(connection) && connectionSet.add(connection);
    }

    public boolean removeConnection(NodeConnection connection) {
        return connectionSet.contains(connection) && connectionSet.remove(connection);
    }

    public boolean isConnectedTo(WebNode node){
        for(NodeConnection connection : connectionSet){
            if(connection.getTarget().equals(node)){
                return true;
            }
        }

        return false;
    }

    public Set<NodeConnection> getConnections() {
        return connectionSet;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }
        Node compare = (Node) obj;
        return hashCode() == compare.hashCode();
    }

    @Override
    public int hashCode() {
        return getPosition().hashCode();
    }

    @Override
    public String toString() {
        return getPosition().toString();
    }
}
