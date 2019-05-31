package org.ploxie.pathfinder.web.path;

import org.ploxie.pathfinder.web.WebNode;

import java.awt.*;
import java.util.ArrayList;

public abstract class WebPath extends ArrayList<WebNode> {

    public abstract void traverse();

    public abstract void draw(Graphics g);

    public WebNode getNext(WebNode current) {
        int index = indexOf(current);
        if (index == -1 || index == size() - 1) {
            return current;
        }
        return get(index + 1);
    }

    public WebNode getFirst() {
        return get(0);
    }

    public WebNode getLast() {
        return get(size() - 1);
    }



}
