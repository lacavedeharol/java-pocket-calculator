package com.lacavedeharol.calculator.view;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * CalculatorFrame class.
 * 
 * @author lacavedeharol
 */
public class CalculatorFrame extends JFrame {

    private final List<CalculatorButton> calculatorButtonList;
    private final CalculatorDisplayText calculatorDisplayResult;
    private final CalculatorDisplayText calculatorDisplayOperation;

    /**
     * Creates a new CalculatorFrame.
     */
    public CalculatorFrame() {
        this.calculatorButtonList = new ArrayList<>();
        this.calculatorDisplayOperation = new CalculatorDisplayText("", Utilities.OPERATION_DISPLAY_HEIGHT);
        this.calculatorDisplayResult = new CalculatorDisplayText("0.0", Utilities.RESULT_DISPLAY_HEIGHT);

        setTitle("Calculator");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        add(createMainPanel());

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Creates the main panel for the calculator.
     * 
     * @return the main panel.
     */
    private JPanel createMainPanel() {

        JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 2, 2));
        mainPanel.setBackground(Utilities.greenWhite);
        mainPanel.setPreferredSize(Utilities.FRAME_SIZE);

        mainPanel.add(calculatorDisplayOperation);
        mainPanel.add(calculatorDisplayResult);

        for (String label : CalculatorConstants.BUTTON_LABELS) {
            CalculatorButton button = new CalculatorButton(label);
            mainPanel.add(button);
            calculatorButtonList.add(button);
        }

        return mainPanel;
    }

    /**
     * Returns the list of calculator buttons.
     * 
     * @return the list of calculator buttons.
     */
    public List<CalculatorButton> getButtonList() {
        return calculatorButtonList;
    }

    /**
     * Updates the result text.
     * 
     * @param text the text to update.
     */
    public void updateResultText(String text) {
        calculatorDisplayResult.setText(text);
    }

    /**
     * Updates the operation text.
     * 
     * @param text the text to update.
     */
    public void updateOperationText(String text) {
        calculatorDisplayOperation.setText(text);
    }
}
