package org.ploxie.pathfinder.web.node;

import org.ploxie.pathfinder.web.connections.NodeConnection;
import org.ploxie.pathfinder.web.connections.NodeWalkConnection;
import org.ploxie.wrapper.Position;
import org.ploxie.wrapper.Positionable;

import java.util.HashSet;
import java.util.Set;

public abstract class Node implements Positionable {

    protected Position position;
    protected Set<NodeConnection> connectionSet = new HashSet<>();

    public Node(int x, int y, int z) {
        this.position = new Position(x,y,z);
    }

    public Node(Position position) {
        this.position = position;
    }

    public Position getPosition(){
        return position;
    }

    public int getX(){
        return getPosition().getX();
    }

    public int getY(){
        return getPosition().getY();
    }

    public int getZ(){
        return getPosition().getZ();
    }

    public double euclideanDistanceSquared(Positionable target){
        return position.euclideanDistanceSquared(target);
    }

    public int distanceTo(Positionable target){
        return position.distanceTo(target);
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
        return position.hashCode();
    }
}
