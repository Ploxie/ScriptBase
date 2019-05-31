package org.ploxie.pathfinder;

import org.ploxie.pathfinder.collision.Reachable;
import org.ploxie.pathfinder.methods.PathExecutor;
import org.ploxie.pathfinder.methods.Pathfinder;
import org.ploxie.pathfinder.web.Web;
import org.ploxie.pathfinder.web.connections.NodeConnection;
import org.ploxie.pathfinder.web.connections.executor.NodeConnectionExecutor;
import org.ploxie.pathfinder.wrapper.DefaultWalker;

import java.util.HashMap;
import java.util.Map;

public abstract class Walker implements Pathfinder, PathExecutor {

    private static Walker walker;

    protected Web web;
    private Reachable reachable;
    private Map<Class<? extends NodeConnection>, NodeConnectionExecutor> connectionExecutors = new HashMap<>();

    public static void create(Walker w){
        walker = w;
    }

    public static void create(Web web){
        create(new DefaultWalker(web));
    }

    public static Walker getInstance(){
        return walker;
    }

    public Walker(Web web){
        this.web = web;
    }

    public Web getWeb(){
        return web;
    }

    public Reachable getReachable(){
        return reachable;
    }

    public void setReachable(Reachable reachable){
        this.reachable = reachable;
    }

    public void addConnectionExecutor(NodeConnectionExecutor executor){
        connectionExecutors.put(executor.getType(), executor);
    }

    public void removeConnectionExecutor(NodeConnectionExecutor executor){
        connectionExecutors.remove(executor.getType());
    }

    public NodeConnectionExecutor getConnectionExecutor(NodeConnection connection){
        return connectionExecutors.get(connection.getClass());
    }

    public boolean executeConnection(NodeConnection connection){
        NodeConnectionExecutor executor = getConnectionExecutor(connection);
        if(executor != null){
            return executor.execute(connection);
        }
        return false;
    }

}
