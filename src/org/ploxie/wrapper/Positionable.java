package org.ploxie.wrapper;

public interface Positionable {

    Position getPosition();

    default int getX() {
        return getPosition().getX();
    }

    default int getY() {
        return getPosition().getY();
    }

    default int getZ() {
        return getPosition().getZ();
    }

    default double euclideanDistanceSquared(Positionable target) {
        return getPosition().euclideanDistanceSquared(target);
    }

    default int distanceTo(Positionable target) {
        return getPosition().distanceTo(target);
    }

}
