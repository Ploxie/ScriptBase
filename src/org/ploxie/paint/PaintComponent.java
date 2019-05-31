package org.ploxie.paint;


import java.awt.*;

public abstract class PaintComponent extends PaintComponentContainer{

    private PaintComponent parent;
    private int x;
    private int y;
    private int width;
    private int height;

    protected abstract void paint(Graphics g);

    public PaintComponent(){

    }

    public PaintComponent(int x, int y, int width, int height){
        setPosition(x,y);
        setDimension(width,height);
    }

    protected void paintComponent(Graphics g){
        paint(g);
        for(PaintComponent child : getChildren()){
            child.paintComponent(g);
        }
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setDimension(int width, int height){
        this.width = width;
        this.height = height;
    }

    public void setX(int x ){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public int getX(){
        return (parent != null ? parent.getX() : 0) + x;
    }

    public int getY(){
        return (parent != null ? parent.getY() : 0) + y;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public void setParent(PaintComponent parent){
        this.parent = parent;
        if(width == 0 && height == 0){
            this.width = parent.width;
            this.height = parent.height;
        }
    }

    @Override
    public void add(PaintComponent component){
        super.add(component);
        component.setParent(this);
    }

}
