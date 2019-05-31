package org.ploxie.pathfinder.web.connections;

import org.ploxie.pathfinder.web.node.Node;

public abstract class NodeConnection {

    protected final Node source;
    protected final Node target;

    public NodeConnection(Node source, Node target){
        this.source = source;
        this.target = target;
    }

    public double getCost(){
        return 0.0;
    }

    public Node getSource(){
        return source;
    }

    public Node getTarget(){
        return target;
    }

    @Override
    public int hashCode() {
        return getSource().hashCode() + getTarget().hashCode();
    }

}
