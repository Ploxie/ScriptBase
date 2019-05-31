package org.ploxie.pathfinder.web.node;

import org.ploxie.pathfinder.web.connections.NodeConnection;
import org.ploxie.pathfinder.web.connections.NodeWalkConnection;
import org.ploxie.pathfinder.wrapper.Position;

import java.util.HashSet;
import java.util.Set;

public abstract class Node extends Position {

    protected Set<NodeConnection> connectionSet = new HashSet<>();

    public Node(int x, int y, int z) {
        super(x, y, z);
    }

    public Node(Position position) {
        super(position.getX(), position.getY(), position.getZ());
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

}
