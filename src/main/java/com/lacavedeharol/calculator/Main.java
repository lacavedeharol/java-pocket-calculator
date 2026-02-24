package com.lacavedeharol.calculator;

import com.lacavedeharol.calculator.controller.CalculatorController;
import com.lacavedeharol.calculator.view.CalculatorFrame;
import javax.swing.SwingUtilities;

/**
 * Main class.
 */
public class Main {

    /**
     * Main method.
     * 
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        /**
         * Create and show the calculator frame on the event dispatch thread.
         */
        SwingUtilities.invokeLater(() -> {
            new CalculatorController(new CalculatorFrame());
        });
    }
}
