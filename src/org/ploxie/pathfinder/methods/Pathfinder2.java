package org.ploxie.pathfinder.methods;

import org.ploxie.pathfinder.web.WebNode;
import org.ploxie.pathfinder.web.path.WebPath;

public interface Pathfinder2 {
	
	WebPath findPath(WebNode start, WebNode end);
	
}
