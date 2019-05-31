package org.ploxie.pathfinder.web.node;

import org.ploxie.wrapper.Position;

public class WebNode extends Node {

    public WebNode(int x, int y, int z) {
        super(x, y, z);
    }

    public WebNode(Position position) {
        super(position);
    }
}
