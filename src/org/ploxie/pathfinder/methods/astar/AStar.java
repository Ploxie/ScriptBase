package org.ploxie.pathfinder.methods.astar;

import org.ploxie.pathfinder.web.connections.NodeConnection;
import org.ploxie.pathfinder.web.node.Node;
import org.ploxie.pathfinder.web.path.NodePath;
import org.ploxie.pathfinder.web.path.Path;

import java.lang.reflect.Array;
import java.util.*;

public class AStar {

    private PriorityQueue<AStarStore> openList;

    private Set<Node> openSet;
    private Set<Node> closedSet;

    private Map<Node, Double> costCache;
    private Map<Node, NodeConnection> pathCache;

    private Node startNode;
    private Node endNode;

    public AStar(){
        openList = new PriorityQueue<>();

        openSet = new HashSet<>();
        closedSet = new HashSet<>();

        costCache = new HashMap<>();
        pathCache = new HashMap<>();
    }

    public Path buildPath(Node start, Node end){
        return buildPath(start, end, false);
    }

    public Path buildPath(Node start, Node end, boolean ignoreNoPath){
        this.startNode = start;
        this.endNode = end;

        openList.add(new AStarStore(start, 0.0D));
        openSet.add(start);
        costCache.put(start, 0.0D);

        while(!openList.isEmpty()){
            AStarStore current = openList.poll();
            openSet.remove(current.getNode());

            closedSet.add(current.getNode());

            if(isEndNode(current.getNode())){
                return new NodePath(startNode, endNode, collectPath());
            }else{
                addNeighbours(current);
            }
        }

        return ignoreNoPath ? new NodePath(startNode, endNode, collectPath()) : null;
    }

    private void addNeighbours(AStarStore store){
        for (NodeConnection connection : store.getNode().getConnections()) {
            Node target = connection.getTarget();
            if (closedSet.contains(target)) {
                continue;
            }

            if (!openSet.contains(target)) {
                double cost = store.getNode().getPosition().distanceTo(target.getPosition());
                double g = costCache.get(store.getNode());
                double heuristic = getHeuristic(store.getNode()) + connection.getCost();
                double totalCost =  g + cost + heuristic;

                pathCache.put(target, connection);
                costCache.put(target, totalCost);

                openList.add(new AStarStore(target, totalCost));
                openSet.add(target);
                continue;
            }
        }
    }

    private List<NodeConnection> collectPath(){
        List<NodeConnection> path = new ArrayList<>();

        NodeConnection currentConnection = pathCache.get(endNode);
        path.add(currentConnection);

        while(true){
            if(currentConnection == null){
                break;
            }

            Node source = currentConnection.getSource();

            if(source.equals(startNode)){
                break;
            }

            currentConnection = pathCache.get(source);
            path.add(currentConnection);
        }

        Collections.reverse(path);

        return path;
    }

    private double getHeuristic(Node current){
        return current.getPosition().euclideanDistanceSquared(endNode.getPosition());
    }

    private boolean isEndNode(Node node){
        return node.equals(endNode);
    }

}
