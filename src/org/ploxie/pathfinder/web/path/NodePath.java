package org.ploxie.pathfinder.web.path;

import org.ploxie.pathfinder.methods.PathExecutor;
import org.ploxie.pathfinder.web.connections.NodeConnection;
import org.ploxie.pathfinder.web.node.Node;

import java.util.List;

public class NodePath implements Path {

    private Node startNode;
    private Node endNode;
    private List<NodeConnection> connections;

    public NodePath(Node start, Node end, List<NodeConnection> connections){
        this.connections = connections;
        this.startNode = start;
        this.endNode = end;
    }

    public Node getStartNode(){
        return startNode;
    }

    public Node getEndNode(){
        return endNode;
    }

    @Override
    public List<NodeConnection> getConnections(){
        return connections;
    }

    @Override
    public boolean traverse(PathExecutor executor) {
        return executor.execute(this);
    }

}
