package com.lacavedeharol.calculator;

import com.lacavedeharol.calculator.controller.CalculatorController;
import com.lacavedeharol.calculator.view.CalculatorFrame;
import javax.swing.SwingUtilities;

/**
 * Main class.
 * 
 * @author lacavedeharol
 */
public class Main {

    /**
     * Main method.
     * 
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CalculatorController(new CalculatorFrame());
        });
    }
}
