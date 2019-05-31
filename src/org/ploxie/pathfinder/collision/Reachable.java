package org.ploxie.pathfinder.collision;

import org.ploxie.wrapper.Position;
import org.ploxie.wrapper.Positionable;

public interface Reachable{

    boolean canReach(Positionable target, Positionable from);

    Position getClosestTo(Positionable position);

}
