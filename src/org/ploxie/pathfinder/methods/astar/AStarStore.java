package org.ploxie.pathfinder.methods.astar;

import org.jetbrains.annotations.NotNull;
import org.ploxie.pathfinder.web.node.Node;

public class AStarStore implements Comparable<AStarStore>{

    private Node node;
    private double cost;

    public AStarStore(Node node, double cost){
        this.node = node;
        this.cost = cost;
    }

    public Node getNode(){
        return node;
    }

    public double getCost(){
        return cost;
    }

    @Override
    public int compareTo(@NotNull AStarStore o) {
        return Double.compare(this.getCost(), o.getCost());
    }
}
