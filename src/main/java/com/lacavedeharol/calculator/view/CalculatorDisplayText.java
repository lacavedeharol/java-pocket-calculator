package com.lacavedeharol.calculator.view;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;

/**
 * CalculatorDisplayText class.
 * 
 * @author lacavedeharol
 */
public class CalculatorDisplayText extends JLabel {

    /**
     * Creates a new CalculatorDisplayText with the specified text and vertical
     * size.
     * 
     * @param text  the text to display on the label.
     * @param vSize the vertical size of the label.
     */
    public CalculatorDisplayText(String text, int vSize) {
        super(text);
        setForeground(Utilities.darkGreen);
        setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        setPreferredSize(new Dimension(Utilities.DISPLAY_WIDTH, vSize));
        setFont(new Font("Dialog", Font.PLAIN, vSize - Utilities.FONT_VERTICAL_PADDING));
    }
}
