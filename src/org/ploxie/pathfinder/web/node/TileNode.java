package org.ploxie.pathfinder.web.node;

import org.ploxie.pathfinder.Walker2;
import org.ploxie.pathfinder.collision.DoorData;
import org.ploxie.pathfinder.collision.region.Region;
import org.ploxie.pathfinder.web.connections.NodeConnection;
import org.ploxie.pathfinder.web.connections.ObjectConnection;
import org.ploxie.pathfinder.web.connections.TileWalkConnection;
import org.ploxie.wrapper.Direction;
import org.ploxie.wrapper.Position;

import java.util.Set;

public class TileNode extends Node {

    public TileNode(int x, int y, int z) {
        super(x, y, z);
    }

    public TileNode(Position position) {
        super(position);
    }

    @Override
    public Set<NodeConnection> getConnections() {
        Set<NodeConnection> connections = super.getConnections();

        Region localRegion = Walker2.getLocalRegion();
        for(Direction direction : Direction.values()){
            if(localRegion.canGoInDirection(getPosition(), direction.getXOffset(), direction.getYOffset())){
                connections.add(new TileWalkConnection(this, new TileNode(getPosition().translate(direction))));
            }else{
                DoorData doorData = localRegion.getDoorData(getPosition());
                if(doorData != null){
                    connections.add(new ObjectConnection(this, new TileNode(getPosition().translate(direction)), doorData.getName(), doorData.getOpenAction(), doorData.getPosition()));
                }else{
                    /*doorData = localRegion.getDoorData(translate(direction));
                    if(doorData != null){
                        connections.add(new ObjectConnection(this, new TileNode(translate(direction)), doorData.getName(), doorData.getOpenAction()));
                    }*/
                }
            }
        }

        return connections;
    }

    public TileNode translate(Direction direction){
        return translate(direction.getXOffset(), direction.getYOffset());
    }

    public TileNode translate(int x, int y){
        return translate(x,y,0);
    }

    public TileNode translate(int x, int y, int z){
        return new TileNode(getX() + x, getY()+y,getZ() + z);
    }
}
