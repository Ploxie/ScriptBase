package org.ploxie.pathfinder.methods;

import org.ploxie.pathfinder.web.path.Path;
import org.ploxie.wrapper.Position;

public interface Pathfinder {

    Path findPath(Position start, Position end);

}
