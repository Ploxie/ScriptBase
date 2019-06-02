package org.ploxie.pathfinder.methods.astar;

import org.ploxie.pathfinder.web.connections.ItemActionConnection;
import org.ploxie.pathfinder.web.connections.NodeConnection;
import org.ploxie.pathfinder.web.connections.NpcConnection;
import org.ploxie.pathfinder.web.node.Node;
import org.ploxie.pathfinder.web.node.TileNode;
import org.ploxie.pathfinder.web.node.WebNode;
import org.ploxie.pathfinder.web.path.LocalPath;
import org.ploxie.pathfinder.web.path.NodePath;
import org.ploxie.pathfinder.web.path.Path;
import org.ploxie.pathfinder.web.path.WebPath;
import org.rspeer.ui.Log;

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



    public LocalPath buildPath(TileNode start, TileNode end){
        return (LocalPath) buildPath(start, end, false, null);
    }

    public WebPath buildPath(WebNode start, WebNode end){
        return (WebPath) buildPath(start, end, false, null);
    }

    public Path buildPath(Node start, AStarEndCondition condition){
        return buildPath(start, null, false, condition);
    }

    public Path buildPath(Node start, Node end){
        return buildPath(start, end, false, null);
    }

    protected Path buildPath(Node start, Node end, boolean ignoreNoPath, AStarEndCondition condition){
        this.startNode = start;
        this.endNode = end;

        openList.add(new AStarStore(start, 0.0D));
        openSet.add(start);
        costCache.put(start, 0.0D);



        while(!openList.isEmpty()){
            AStarStore current = openList.poll();
            openSet.remove(current.getNode());

            closedSet.add(current.getNode());

            AStarEndCondition isEndCondition = condition;
            if(isEndCondition == null){
                isEndCondition = isEndNode();
            }


            if(isEndCondition.validate(current.getNode())){
                endNode = current.getNode();
                if(start instanceof TileNode){
                    LocalPath path = new LocalPath(startNode, endNode, collectPath());
                    path.setCost(costCache.get(endNode));
                    return path;
                }else if(start instanceof WebNode){
                    WebPath path = new WebPath(startNode, endNode, collectPath());
                    path.setCost(costCache.get(endNode));
                    return path;
                }
                return new NodePath(startNode, endNode, collectPath());
            }else{
                addNeighbours(current);
            }
        }

        return ignoreNoPath ? new NodePath(startNode, endNode, collectPath()) : null;
    }

    private void addNeighbours(AStarStore store){
        for (NodeConnection connection : store.getNode().getConnections()) {

            if(!connection.canUse()){
                Log.info(connection);
                continue;
            }
            Node target = connection.getTarget();
            if (closedSet.contains(target)) {
                continue;
            }



            if (!openSet.contains(target)) {
                double cost = store.getNode().getPosition().distanceTo(target.getPosition());
                double g = costCache.get(store.getNode());
                double heuristic = getHeuristic(store.getNode()) + connection.getCost();
                double totalCost =  g + heuristic;

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

    private AStarEndCondition isEndNode(){
        return node -> node.equals(endNode);
    }

}
