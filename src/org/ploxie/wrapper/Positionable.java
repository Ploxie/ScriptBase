package org.ploxie.wrapper;

public interface Positionable {

    Position getPosition();

    int getX();
    int getY();
    int getZ();

    double euclideanDistanceSquared(Positionable target);

    int distanceTo(Positionable target);

}
