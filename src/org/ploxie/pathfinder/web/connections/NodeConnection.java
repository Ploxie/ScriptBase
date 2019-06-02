package org.ploxie.pathfinder.web.connections;

import org.ploxie.pathfinder.web.node.Node;

public abstract class NodeConnection {

    protected Node source;
    protected Node target;

    public NodeConnection(Node source, Node target){
        this.source = source;
        this.target = target;
    }

    public boolean canUse(){
        return true;
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

    public abstract Class<? extends NodeConnection> getType();
}
