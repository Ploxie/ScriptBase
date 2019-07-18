package org.ploxie.pathfinder.web.connections;

import org.ploxie.pathfinder.web.node.Node;

public class TileWalkConnection extends WalkConnection {

    public TileWalkConnection(Node source, Node target) {
        super(source, target);
    }

    @Override
    public Class<? extends NodeConnection> getType() {
        return TileWalkConnection.class;
    }

}

