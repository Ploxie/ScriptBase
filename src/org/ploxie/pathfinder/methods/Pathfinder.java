package org.ploxie.pathfinder.methods;

import org.ploxie.pathfinder.web.path.LocalPath;
import org.ploxie.pathfinder.web.path.Path;
import org.ploxie.pathfinder.web.path.WebPath;
import org.ploxie.wrapper.Position;

public interface Pathfinder {

    Path findPath(Position start, Position end);

    LocalPath findLocalPath(Position start, Position end);

    WebPath findWebPath(Position start, Position end);

}
