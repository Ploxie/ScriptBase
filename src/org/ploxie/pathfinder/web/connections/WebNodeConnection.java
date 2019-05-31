package org.ploxie.pathfinder.web.connections;

import org.ploxie.pathfinder.web.WebNode;

public abstract class WebNodeConnection{

    protected final WebNode source;
    protected final WebNode target;

    public WebNodeConnection(WebNode source, WebNode target) {
        this.source = source;
        this.target = target;
    }

    public abstract void traverse();

    public WebNode getSource() {
        return source;
    }

    public WebNode getTarget() {
        return target;
    }

    @Override
    public int hashCode() {
        WebNode source = getSource();
        int value = 0;
        if (source != null) {
            value += source.hashCode();
        }
        source = getTarget();
        if (source != null) {
            value += source.hashCode();
        }
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof WebNodeConnection)) {
            return false;
        }
        WebNodeConnection wnc = (WebNodeConnection) o;
        if (getSource() == null && wnc.getSource() != null) {
            return false;
        } else if (!getSource().equals(wnc.getSource())) {
            return false;
        }
        if (getTarget() == null && wnc.getTarget() != null) {
            return false;
        } else if (!getTarget().equals(wnc.getTarget())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getSource() + " -> " + this.getTarget();
    }

}
