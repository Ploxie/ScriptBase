package org.ploxie.pathfinder.web.connections;

import org.ploxie.pathfinder.web.node.Node;

public abstract class WalkConnection extends NodeConnection {

    public WalkConnection(Node source, Node target) {
        super(source, target);
    }

    public double getCost(){
        return getSource().getPosition().euclideanDistanceSquared(getTarget().getPosition());
    }

    @Override
    public Class<? extends NodeConnection> getType() {
        return WalkConnection.class;
    }
}
