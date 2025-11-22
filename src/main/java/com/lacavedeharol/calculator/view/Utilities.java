package com.lacavedeharol.calculator.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JComponent;

/**
 * Utility class for calculator components.
 */
public class Utilities {

    public static final Color darkGreen = Color.decode("#252525");
    public static final Color moldGreen = Color.decode("#4b564d");
    public static final Color lightGreen = Color.decode("#9aa57c");
    public static final Color greenWhite = Color.decode("#e0e9c4");

    public static final int DISPLAY_WIDTH = 262,
            FONT_VERTICAL_PADDING = 16,
            OPERATION_DISPLAY_HEIGHT = 32,
            RESULT_DISPLAY_HEIGHT = 48;
    public static final Dimension FRAME_SIZE = new Dimension(266, 336);

    public static RoundRectangle2D.Float roundRectangle(JComponent component) {
        return new RoundRectangle2D.Float(0, 0, component.getWidth() - 1, component.getHeight() - 1, 8, 8);
    }
}
