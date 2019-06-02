package org.ploxie.pathfinder.methods.astar;

import org.ploxie.pathfinder.web.node.Node;

public interface AStarEndCondition{

    boolean validate(Node node);

}
