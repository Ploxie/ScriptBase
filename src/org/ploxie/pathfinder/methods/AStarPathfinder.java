package org.ploxie.pathfinder.methods;

import org.ploxie.pathfinder.web.WebNode;
import org.ploxie.pathfinder.web.connections.WebNodeConnection;
import org.ploxie.pathfinder.web.path.WebPath;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

public class AStarPathfinder implements Pathfinder2 {

    protected PriorityQueue<WebNode> openList;
    protected HashSet<WebNode> closedSet;

    protected WebNode start;
    protected WebNode end;

    @Override
    public WebPath findPath(WebNode start, WebNode end) {
        this.start = start;
        this.end = end;

        this.openList = new PriorityQueue<>(Comparator.comparingDouble(WebNode::getTotalCost));
        this.closedSet = new HashSet<>();

        this.openList.add(start);

        while (openList.size() > 0) {
            WebNode currentNode = openList.poll();
            closedSet.add(currentNode);

            if (isEndNode(currentNode)) {
                return backtracePath(currentNode);
            } else {

                addNeighbours(currentNode);
            }
        }
        return null;
    }

    protected void addNeighbours(WebNode node) {

        for (WebNodeConnection connection : node.getConnections()) {
            WebNode target = connection.getTarget();
            if (closedSet.contains(target)) {
                continue;
            }

            if (!openList.contains(target)) {
                double cost = node.distanceTo(target);
                double g = node.getGCost()+cost;
                target.calculateHeuristic(end);

                target.setParent(node);
                target.setGCost(g);

                openList.add(target);
                continue;
            }

           /* double cost = node.distanceTo(target);
            double g = node.getGCost() + cost;

            if ((g) < target.getGCost()) {
                target.setParent(node);
                target.setGCost(g + cost);

                openList.remove(target);
                openList.add(target);
            }*/
        }
    }

    protected WebPath backtracePath(WebNode node) {
        /*WebPath path = new WebPath();
        org.rspeer.runetek.api.movement.pathfinding.region.graph.TilePath

        path.add(node);
        WebNode parent;
        while ((parent = node.getParent()) != null) {
            path.add(0,parent);
            node = parent;
            if(node.equals(start)){
                break;
            }
        }



        return path;*/
        return null;

    }

    protected boolean isEndNode(WebNode node) {
        return node.equals(end);
    }

}
