package org.ploxie.paint.theme;


import java.awt.*;

public class PaintDefaultTheme implements PaintTheme {

    @Override
    public Color getPanelBorderColor() {
        return new Color(77,85,97);
    }

    @Override
    public Color getPanelBackgroundColor() {
        return new Color(54,65,76);
    }

    @Override
    public Font getLabelFont() {
        return new Font("Arial", Font.PLAIN, 14);
    }

    @Override
    public Color getLabelFontColor() {
        return Color.white;
    }

    @Override
    public Color getLabelBorderColor() {
        return new Color(0,0,0,0);
    }

    @Override
    public Color getLabelBackgroundColor() {
        return new Color(0,0,0,0);
    }
}
