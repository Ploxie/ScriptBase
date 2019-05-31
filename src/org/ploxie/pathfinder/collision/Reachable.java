package org.ploxie.pathfinder.collision;

import org.ploxie.pathfinder.wrapper.Position;

public interface Reachable{

    boolean canReach(Position target, Position from);

    Position getClosestTo(Position position);

}
