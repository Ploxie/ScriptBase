package org.ploxie.pathfinder.web.connections.executor;

import org.ploxie.pathfinder.web.connections.NodeConnection;

public interface NodeConnectionExecutor<T extends NodeConnection>{

    boolean execute(T connection);

    Class<T> getType();

}
