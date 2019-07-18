package org.ploxie.pathfinder.web.connections;

import org.ploxie.pathfinder.web.node.Node;

public class NodeWalkConnection extends WalkConnection {

    public NodeWalkConnection(Node source, Node target) {
        super(source, target);
    }


    @Override
    public Class<? extends NodeConnection> getType() {
        return NodeWalkConnection.class;
    }
}
