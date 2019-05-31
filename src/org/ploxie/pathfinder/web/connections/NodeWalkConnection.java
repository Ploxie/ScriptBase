package org.ploxie.pathfinder.web.connections;

import org.ploxie.pathfinder.Walker2;
import org.ploxie.pathfinder.web.node.Node;
import org.rspeer.ui.Log;

public class NodeWalkConnection extends WalkConnection {

    public NodeWalkConnection(Node source, Node target) {
        super(source, target);
    }

}
