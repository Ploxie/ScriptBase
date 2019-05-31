package org.ploxie.paint;

import org.ploxie.paint.theme.PaintDefaultTheme;
import org.ploxie.paint.theme.PaintTheme;

import java.awt.*;

public class PaintManager extends PaintComponentContainer{

    private static final PaintManager instance = new PaintManager();

    private PaintTheme paintTheme = new PaintDefaultTheme();

    private void paintComponents(Graphics g){
        for(PaintComponent child : getChildren()){
            child.paintComponent(g);
        }
    }

    public static void setPaintTheme(PaintTheme theme){
        instance.paintTheme = theme;
    }

    public static PaintTheme getPaintTheme(){
        return instance.paintTheme;
    }

    public static void addComponent(PaintComponent component){
        instance.add(component);
    }

    public static void paint(Graphics g){
        instance.paintComponents(g);
    }

}
