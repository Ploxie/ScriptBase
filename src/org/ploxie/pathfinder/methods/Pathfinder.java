package org.ploxie.pathfinder.methods;

import org.ploxie.pathfinder.web.path.Path;
import org.ploxie.pathfinder.wrapper.Position;

public interface Pathfinder {

    Path findPath(Position start, Position end);

}
