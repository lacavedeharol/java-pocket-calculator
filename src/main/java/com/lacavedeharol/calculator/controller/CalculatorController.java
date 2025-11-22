package com.lacavedeharol.calculator.controller;

import com.lacavedeharol.calculator.view.CalculatorButton;
import com.lacavedeharol.calculator.view.CalculatorConstants;
import com.lacavedeharol.calculator.view.CalculatorFrame;
import com.lacavedeharol.calculator.model.CalculatorModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller class for the calculator.
 */
public class CalculatorController implements ActionListener, KeyListener {

    private final CalculatorFrame calculatorFrame;
    private final Map<String, CalculatorButton> buttonMap;

    private String displayResult = "0.0";
    private String displayOperation = "";

    /**
     * Model performs statically, not instantiated through the controllers
     * constructor, or anywhere.
     *
     * @param calculatorFrame
     */
    public CalculatorController(CalculatorFrame calculatorFrame) {
        this.calculatorFrame = calculatorFrame;
        this.buttonMap = new HashMap<>();

        calculatorFrame.getButtonList().forEach(button -> {
            button.addActionListener(this);
            buttonMap.put(button.getText(), button);
        });

        calculatorFrame.addKeyListener(this);
        calculatorFrame.setFocusable(true);
        calculatorFrame.requestFocusInWindow();
        updateDisplay();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        processCommand(e.getActionCommand());
    }

    /**
     * Processes the command based on the action command.
     * 
     * @param command
     */
    private void processCommand(String command) {
        if (command.matches("[0-9]")) {
            appendNumber(command);
        } else if (command.matches("[/*\\-+]")) {
            handleOperator(command);
        } else {
            switch (command) {
                case CalculatorConstants.BTN_CLEAR -> {
                    displayOperation = "";
                    displayResult = "0.0";
                }
                case CalculatorConstants.BTN_CLEAR_ENTRY -> {
                    displayResult = "0.0";
                }
                case CalculatorConstants.BTN_DOT -> {
                    appendDecimalPoint();
                }
                case CalculatorConstants.BTN_EQUALS -> {
                    if (!displayOperation.isEmpty()) {
                        calculateResult();
                    }
                }
            }
        }
        updateDisplay();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used, handled in keyPressed/keyReleased for visual feedback
    }

    @Override
    public void keyPressed(KeyEvent e) {
        CalculatorButton button = getButton(e);
        if (button != null) {
            button.getModel().setPressed(true);
            button.getModel().setArmed(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        CalculatorButton button = getButton(e);
        if (button != null) {
            button.getModel().setPressed(false);
            button.getModel().setArmed(false);
        }
    }

    /**
     * Retrieves the button associated with the given key event.
     * 
     * @param e
     * @return
     */
    private CalculatorButton getButton(KeyEvent e) {
        char c = e.getKeyChar();
        if (Character.isDigit(c) || "+-*/.".indexOf(c) != -1) {
            return buttonMap.get(String.valueOf(c));
        }

        int code = e.getKeyCode();
        if (code == KeyEvent.VK_ENTER)
            return buttonMap.get(CalculatorConstants.BTN_EQUALS);
        if (code == KeyEvent.VK_ESCAPE)
            return buttonMap.get(CalculatorConstants.BTN_CLEAR);
        if (code == KeyEvent.VK_BACK_SPACE || code == KeyEvent.VK_DELETE)
            return buttonMap.get(CalculatorConstants.BTN_CLEAR_ENTRY);

        return null;
    }

    /**
     * Appends a number to the display result.
     * 
     * @param number
     */
    private void appendNumber(String number) {
        if (displayResult.equals("0.0") || "Error".equals(displayResult)) {
            displayResult = number;
        } else {
            displayResult += number;
        }
    }

    private void appendDecimalPoint() {
        if (!displayResult.contains(CalculatorConstants.BTN_DOT)) {
            displayResult += CalculatorConstants.BTN_DOT;
        }
    }

    /**
     * Handles the operator button press.
     * 
     * @param operator
     */
    private void handleOperator(String operator) {
        // Allow unary minus if displayResult is empty and we are starting a negative
        // number
        if (operator.equals(CalculatorConstants.BTN_SUBTRACT) && displayResult.isEmpty()) {
            displayResult = operator;
            return;
        }

        if (isLastCharacterOperator() && displayResult.isEmpty()) {
            displayOperation = displayOperation.substring(0, displayOperation.length() - 1) + operator;
        } else if (!displayResult.isEmpty() && !"Error".equals(displayResult)) {

            displayOperation += displayResult + operator;
            displayResult = "";
        }
    }

    /**
     * Calculates the result of the expression in the display operation.
     */
    private void calculateResult() {
        if (displayResult.isEmpty()) {
            return;
        }
        String fullExpression = displayOperation + displayResult;
        displayResult = CalculatorModel.calculate(fullExpression);
        displayOperation = "";
    }

    /**
     * Checks if the last character in the display operation is an operator.
     *
     * @return true if the last character is an operator, false otherwise
     */
    private boolean isLastCharacterOperator() {
        if (displayOperation.isEmpty()) {
            return false;
        }
        char lastChar = displayOperation.charAt(displayOperation.length() - 1);
        return "+-*/".indexOf(lastChar) != -1;
    }

    private void updateDisplay() {
        calculatorFrame.updateOperationText(displayOperation);
        calculatorFrame.updateResultText(displayResult);
    }
}
