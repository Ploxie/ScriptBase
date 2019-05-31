package org.ploxie.pathfinder.web;

import org.ploxie.pathfinder.web.connections.NodeConnection;
import org.ploxie.pathfinder.wrapper.Position;
import org.ploxie.pathfinder.web.node.WebNode;
import org.rspeer.ui.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Web extends HashSet<WebNode> {

    @Override
    public boolean add(WebNode e) {
        return addWebNode(e);
    }

    public boolean addWebNode(WebNode node) {
        if (contains(node)) {
            return false;
        }

        return super.add(node);
    }

    public boolean addConnection(WebNode a, WebNode b) {
        if (!contains(a)) {
            add(a);
        }
        if (!contains(b)) {
            add(b);
        }
        return a.addConnection(b) && b.addConnection(a);
    }

    public WebNode getNode(int x, int y, int plane) {
        WebNode newNode = new WebNode(x, y, plane);
        for (WebNode node : this) {
            if (node.equals(newNode)) {
                return node;
            }
        }
        return newNode;
    }

    public WebNode getNode(Position position) {
        return getNode(position.getX(), position.getY(), position.getZ());
    }

    public WebNode getNearestNode(Position target) {
        WebNode best = null;
        double distance = Double.MAX_VALUE;
        for (WebNode node : this) {
            double currentDistance = node.distanceTo(target);
            if (node.distanceTo(target) < distance) {
                best = node;
                distance = currentDistance;
            }
        }

        return best;
    }

    public boolean removeWebNode(WebNode node) {
        List<NodeConnection> connectionsToRemove = new ArrayList<>();
        for (NodeConnection connection : node.getConnections()) {
            connectionsToRemove.add(connection);
        }
        for (NodeConnection connection : connectionsToRemove) {
            node.removeConnection(connection);
        }
        return removeConnectionsTo(node) && remove(node);
    }

    private boolean removeConnectionsTo(WebNode target) {
        for (WebNode node : this) {
            List<NodeConnection> connectionsToRemove = new ArrayList<>();
            for (NodeConnection connection : node.getConnections()) {
                if (connection.getTarget().equals(target)) {
                    connectionsToRemove.add(connection);
                }
            }
            for (NodeConnection connection : connectionsToRemove) {
                node.removeConnection(connection);
            }
        }

        return true;
    }

}
