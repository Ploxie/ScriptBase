package org.ploxie.pathfinder.web;

import org.ploxie.pathfinder.Walker2;
import org.ploxie.pathfinder.methods.astar.AStar;
import org.ploxie.pathfinder.web.area.WebArea;
import org.ploxie.pathfinder.web.connections.NodeConnection;
import org.ploxie.pathfinder.web.node.DynamicNode;
import org.ploxie.pathfinder.web.node.Node;
import org.ploxie.pathfinder.web.path.Path;
import org.ploxie.pathfinder.web.webbank.WebBank;
import org.ploxie.pathfinder.web.webbank.WebBankNode;
import org.ploxie.wrapper.Position;
import org.ploxie.pathfinder.web.node.WebNode;
import org.ploxie.wrapper.Positionable;
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

    public boolean addConnection(NodeConnection connection){
        if(connection.getSource() instanceof WebNode){
            add((WebNode)connection.getSource());
        }
        if(connection.getTarget() instanceof WebNode){
            add((WebNode)connection.getTarget());
        }
        return connection.getSource().addConnection(connection);
    }

    public void addWebArea(WebArea area){
        area.addNodes(this);
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

    public WebBank getNearestBank(){
        return getNearestBank(Walker2.getLocalPlayerPosition());
    }

    public WebBank getNearestBank(Position from){
        Path toNearestBank = new AStar().buildPath(getNearestStaticNode(from), new WebBank.WebBankCondition());
        if(toNearestBank == null){
            return null;
        }
        Node endNode = toNearestBank.getEndNode();
        if(endNode == null || !(endNode instanceof WebBankNode)){
            return null;
        }

        return WebBank.getBank(endNode);
    }

    public WebNode getNearestNode(Positionable target){
        WebNode best = null;
        double distance = Double.MAX_VALUE;
        for (WebNode node : this) {
            double currentDistance = node.getPosition().distanceTo(target);
            if (currentDistance < distance) {
                best = node;
                distance = currentDistance;
            }
        }

        return best;
    }

    public WebNode getNearestStaticNode(int x, int y, int z){
        return getNearestStaticNode(new Position(x,y,z));
    }

    public WebNode getNearestStaticNode(WebNode existingNode){
        WebNode best = null;
        double distance = Double.MAX_VALUE;
        for (WebNode node : this) {
            if (node.equals(existingNode)) {
                continue;
            }

            double currentDistance = node.getPosition().distanceTo(existingNode);
            if (currentDistance < distance) {
                best = node;
                distance = currentDistance;
            }
        }

        return best;
    }

    public WebNode getNearestStaticNode(Positionable target) {
        WebNode best = null;
        double distance = Double.MAX_VALUE;
        for (WebNode node : this) {
            if (node instanceof DynamicNode) {
                continue;
            }

            double currentDistance = node.getPosition().distanceTo(target);
            if (currentDistance < distance) {
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
