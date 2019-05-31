package org.ploxie.pathfinder.web.path;

import org.ploxie.pathfinder.web.connections.NodeConnection;
import org.ploxie.pathfinder.web.node.Node;

import java.util.List;

public class WebPath extends NodePath {

    public WebPath(Node start, Node end, List<NodeConnection> connections) {
        super(start, end, connections);
    }

}
