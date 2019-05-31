package org.ploxie.paint.components;

import org.ploxie.paint.PaintComponent;
import org.ploxie.paint.PaintManager;

import java.awt.*;

public class PaintPanel extends PaintComponent {

    private Color borderColor = PaintManager.getPaintTheme().getPanelBorderColor();
    private Color backgroundColor = PaintManager.getPaintTheme().getPanelBackgroundColor();

    public PaintPanel(){

    }

    public PaintPanel(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void setBorderColor(Color color){
        this.borderColor = color;
    }

    public void setBackgroundColor(Color color){
        this.backgroundColor = color;
    }

    @Override
    protected void paint(Graphics g) {
        g.setColor(backgroundColor);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
        g.setColor(borderColor);
        g.drawRect(getX(), getY(), getWidth(), getHeight());
    }

}
