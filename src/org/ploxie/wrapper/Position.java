package org.ploxie.wrapper;

public class Position extends Triplet<Integer, Integer, Integer> implements Positionable{

    protected int cached_hash = -1;

    public Position(int x, int y, int z) {
        super(x, y, z);
    }

    public Position getPosition(){
        return this;
    }

    public int getX() {
        return this.getFirst();
    }

    public int getY() {
        return this.getSecond();
    }

    public int getZ() {
        return this.getThird();
    }

    public Position translate(Direction direction){
        return translate(direction.getXOffset(), direction.getYOffset());
    }

    public Position translate(int x, int y){
        return translate(x,y,0);
    }

    public Position translate(int x, int y, int z){
        return new Position(getX() + x, getY()+y,getZ() + z);
    }

    public double euclideanDistanceSquared(Positionable target){
        return Math.sqrt(Math.pow((double)(target.getX() - getX()), 2.0D)) + Math.sqrt(Math.pow((double)(target.getY() - getY()), 2.0D));
    }

    public int distanceTo(Positionable target) {
        int distX = Math.abs(getX() - target.getX());
        int distY = Math.abs(getY() - target.getY());
        return distX + distY;
    }

    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }
        Position compare = (Position) obj;
        return hashCode() == compare.hashCode();
        //return getX() == compare.getX() && getY() == compare.getY() && getZ() == compare.getZ();
    }

    @Override
    public int hashCode() {
        if(cached_hash == -1) {
            cached_hash = ((getX() & 0b1111_1111_1111_111) << 17) | ((getY() & 0b1111_1111_1111_111) << 2) | (getZ() & 0b11);
        }
        return cached_hash;
    }

    @Override
    public String toString() {
        return "("+getX()+", "+getY()+", "+getZ()+")";
    }

}