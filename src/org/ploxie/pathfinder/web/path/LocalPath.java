package org.ploxie.pathfinder.web.path;

import org.ploxie.pathfinder.web.connections.NodeConnection;
import org.ploxie.pathfinder.web.node.Node;

import java.util.List;

public class LocalPath extends NodePath {

    public LocalPath(Node start, Node end, List<NodeConnection> connections) {
        super(start, end, connections);
    }

}
