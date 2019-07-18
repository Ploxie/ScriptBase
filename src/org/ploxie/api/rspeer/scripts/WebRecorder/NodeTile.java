package org.ploxie.api.rspeer.scripts.WebRecorder;

import org.ploxie.wrapper.Position;
import org.ploxie.wrapper.Positionable;
import org.rspeer.runetek.api.movement.position.Area;

import java.awt.*;
import java.util.Random;

public class NodeTile implements Positionable {

    private Position center;
    private int radius;

    private Area area;
    private Color color;

    public NodeTile(Position center, int radius){
        this.center = center;
        this.radius = radius;

        Random random = new Random(center.hashCode());

        this.area = Area.rectangular(getPosition().getX(), getPosition().getY(), getPosition().getX()+getRadius(), getPosition().getY()+getRadius());
        this.color = new Color(100+ random.nextInt(150),100+ random.nextInt(150),100+ random.nextInt(150));
    }


    @Override
    public Position getPosition() {
        return center;
    }

    public int getRadius() {
        return radius;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }
        NodeTile compare = (NodeTile) obj;
        return compare.getPosition().hashCode() == getPosition().hashCode();
    }

    @Override
    public String toString() {
        return center.toString() + " " + radius;
    }

    public Area getArea() {
        return area;
    }

    public Color getColor() {
        return color;
    }

}
