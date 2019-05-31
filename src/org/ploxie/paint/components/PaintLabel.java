package org.ploxie.paint.components;

import org.ploxie.paint.PaintManager;

import java.awt.*;

public class PaintLabel extends PaintPanel {

    private Color fontColor = PaintManager.getPaintTheme().getLabelFontColor();
    private Font font = PaintManager.getPaintTheme().getLabelFont();
    private String text = "";

    public PaintLabel(){
        initialize();
    }

    public PaintLabel(String text){
        setText(text);
        initialize();
    }

    private void initialize(){
        setBackgroundColor(PaintManager.getPaintTheme().getLabelBackgroundColor());
        setBorderColor(PaintManager.getPaintTheme().getLabelBorderColor());
    }

    public void setFont(Font font){
        this.font = font;
    }

    public Font getFont(){
        return font;
    }

    public void setText(String text){
        this.text = text;
    }

    public String getText(){
        return text;
    }

    public void setFontColor(Color color){
        this.fontColor = color;
    }

    public Color getFontColor(){
        return fontColor;
    }

    @Override
    protected void paint(Graphics g) {
        super.paint(g);
        g.setColor(getFontColor());
        g.setFont(getFont());
        g.drawString(getText(), getX(), getY());
    }
}
