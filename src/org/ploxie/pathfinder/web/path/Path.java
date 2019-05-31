package org.ploxie.pathfinder.web.path;

import org.ploxie.pathfinder.methods.PathExecutor;
import org.ploxie.pathfinder.web.connections.NodeConnection;

import java.util.List;

public interface Path {

    boolean traverse(PathExecutor executor);

    List<NodeConnection> getConnections();

}
