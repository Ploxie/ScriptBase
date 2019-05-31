package org.ploxie.pathfinder.web;

import org.ploxie.pathfinder.collision.RegionData;
import org.ploxie.pathfinder.web.connections.WebNodeConnection;
import org.ploxie.pathfinder.wrapper.Position;
import org.ploxie.pathfinder.web.connections.WalkConnection2;

import java.util.LinkedList;

public class WebNode extends Position {

    private final LinkedList<WebNodeConnection> connections;

    private WebNode parent;

    private double gCost;
    private double heuristic;

    private Position regionPosition;

    public WebNode(int x, int y, int plane) {
        super(x, y, plane);
        this.regionPosition = RegionData.worldToRegionPosition(this);

        this.connections = new LinkedList<>();
    }

    public WebNode(Position position) {
        super(position.getX(), position.getY(), position.getZ());
        this.regionPosition = RegionData.worldToRegionPosition(this);

        this.connections = new LinkedList<>();
    }

    public boolean addConnection(WebNode node) {
        WebNodeConnection connection = new WalkConnection2(this, node);
        if (connections.contains(connection)) {
            return false;
        }
        return addConnection(connection);
    }

    protected boolean addConnection(WebNodeConnection connection) {
        return !connections.contains(connection) && connections.add(connection);
    }

    protected boolean removeConnection(WebNodeConnection connection) {
        return connections.contains(connection) && connections.remove(connection);
    }

    public boolean isConnectedTo(WebNode target) {
        return getConnection(target) != null;
    }

    public WebNodeConnection getConnection(WebNode node) {
        for (WebNodeConnection e : connections) {
            if (e.getTarget().equals(node)) {
                return e;
            }
        }
        return null;
    }

    public double calculateHeuristic(WebNode goal, double stepCost) {
        heuristic = distanceTo(goal) * stepCost;
        return heuristic;
    }

    public double calculateHeuristic(WebNode goal) {
        return calculateHeuristic(goal, 1);
    }

    public void setGCost(double cost) {
        this.gCost = cost;
    }

    public double getGCost() {
        return gCost;
    }

    public double getTotalCost() {
        return gCost + heuristic;
    }

    public LinkedList<WebNodeConnection> getConnections() {
        return connections;
    }

    public Position getRegionPosition() {
        return this.regionPosition;
    }

    public int getPlane() {
        return getZ();
    }

    public void setParent(WebNode parent) {
        this.parent = parent;
    }

    public WebNode getParent() {
        return parent;
    }

}
