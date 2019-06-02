package org.ploxie.pathfinder.web.path;

import org.ploxie.pathfinder.Walker;
import org.ploxie.pathfinder.methods.PathExecutor;
import org.ploxie.pathfinder.web.connections.NodeConnection;
import org.ploxie.pathfinder.web.node.Node;

import java.util.List;

public interface Path {

    default boolean traverse() { return traverse(Walker.getInstance()); }

    boolean traverse(PathExecutor executor);

    List<NodeConnection> getConnections();

    Node getStartNode();

    Node getEndNode();

    double getCost();

}
