package org.ploxie.pathfinder.web.connections;

import org.ploxie.pathfinder.web.node.Node;

public abstract class DynamicWalkConnection extends NodeWalkConnection {

    public DynamicWalkConnection() {
        super(null, null);
    }

    public abstract Node getTarget();

    public abstract Node getSource();
}
