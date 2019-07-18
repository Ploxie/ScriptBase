package org.ploxie.pathfinder.web.path;

import org.ploxie.pathfinder.web.connections.NodeConnection;
import org.ploxie.pathfinder.web.node.WebNode;

import java.util.List;

public class WebPath extends NodePath {

    public WebPath(WebNode start, WebNode end, List<NodeConnection> connections) {
        super(start, end, connections);
    }




}
