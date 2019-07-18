package org.ploxie.pathfinder.collision.region;

import org.ploxie.pathfinder.collision.CollisionData;
import org.ploxie.pathfinder.collision.DoorData;
import org.ploxie.wrapper.Direction;
import org.ploxie.wrapper.Position;

public abstract class Region {

    private Position base;

    private int[][] collisionData;

    public Region(Position base, int[][] collisionData){
        this.base = base;
        this.collisionData = collisionData;
    }

    public abstract DoorData getDoorData(Position position);

    public CollisionData get(Position worldPosition){
        int xOffset = worldPosition.getX()-base.getX();
        int yOffset = worldPosition.getY()-base.getY();

        if(xOffset < 0 || xOffset >= collisionData.length){
            return null;
        }

        if(yOffset < 0 || yOffset >= collisionData[0].length){
            return null;
        }
        return new CollisionData(collisionData[xOffset][yOffset]);
    }

    public boolean isInside(Position worldPosition){
        return get(worldPosition) != null;
    }

    public boolean canGoInDirection(Position from, Direction direction){
        return canGoInDirection(from, direction.getXOffset(), direction.getYOffset());
    }

    public boolean canGoInDirection(Position from, int xDir, int yDir){
        Position to = from.translate(xDir, yDir, from.getZ());

        int x = from.getX();
        int y = from.getY();

        /*if(xDir > 1){
            x = from.translate(xDir -1, 0, 0).getX();
        }
        if(xDir < 1){
            x = from.translate(xDir + 1, 0, 0).getX();
        }

        if(yDir > 1){
            y = from.translate(0, yDir -1, 0).getY();
        }
        if(yDir < 1){
            y = from.translate(0, yDir +1, 0).getY();
        }

        from = new Position(x,y, from.getZ());*/

        if(!isInside(to)){
            return false;
        }

        if(!get(to).isWalkable()){
            return false;
        }
        if(get(from).blockedInDirection(xDir, yDir)){
            return false;
        }

        if(xDir != 0 && yDir != 0){
            return get(from.translate(xDir, 0, 0)).isWalkable() && get(from.translate(0, yDir, 0)).isWalkable();
        }

        return true;
    }
}
