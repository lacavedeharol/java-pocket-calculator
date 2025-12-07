package com.lacavedeharol.calculator.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

/**
 * Calculator model class.
 */
public class CalculatorModel {

    private static final Pattern TOKEN_PATTERN = Pattern.compile("((?<=[-+*/])|(?=[-+*/]))");

    /**
     * Calculates the result of the given input operation.
     * 
     * @param inputOperation
     * @return
     */
    public static String calculate(String inputOperation) {
        try {
            String postfix = infixToPostfix(inputOperation);
            double result = evaluatePostfix(postfix);

            if (result == (long) result) {
                return String.format("%d", (long) result);
            } else {
                return String.format("%s", result);
            }
        } catch (Exception e) {
            return "Error";
        }
    }

    /**
     * Converts an infix expression to a postfix expression.
     * 
     * @param infix the infix expression to convert.
     * @return the postfix expression.
     */
    private static String infixToPostfix(String infix) {
        StringBuilder postfix = new StringBuilder();
        Stack<String> stack = new Stack<>();

        String[] tokens = TOKEN_PATTERN.split(infix);
        List<String> processedTokens = new ArrayList<>();

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            if (token.equals("-")) {

                boolean isFirstToken = (i == 0);
                boolean isAfterOperator = (i > 0 && isOperator(tokens[i - 1]));

                if (isFirstToken || isAfterOperator) {
                    processedTokens.add("0");
                }
            }
            processedTokens.add(token);
        }

        for (String token : processedTokens) {
            if (isNumeric(token)) {
                postfix.append(token).append(" ");
            } else if (isOperator(token)) {
                while (!stack.isEmpty() && hasHigherOrEqualPrecedence(stack.peek(), token)) {
                    postfix.append(stack.pop()).append(" ");
                }
                stack.push(token);
            }
        }

        while (!stack.isEmpty()) {
            postfix.append(stack.pop()).append(" ");
        }
        return postfix.toString().trim();
    }

    /**
     * Evaluates a postfix expression.
     * 
     * @param postfix the postfix expression to evaluate.
     * @return the result of the evaluation.
     */
    private static double evaluatePostfix(String postfix) {
        Stack<Double> stack = new Stack<>();
        StringTokenizer tokenizer = new StringTokenizer(postfix);

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            if (isNumeric(token)) {
                stack.push(Double.valueOf(token));
            } else if (isOperator(token)) {
                double b = stack.pop();
                double a = stack.pop();
                stack.push(applyOperator(a, b, token));
            }
        }
        return stack.pop();
    }

    /**
     * Returns the precedence of an operator.
     * 
     * @param operator the operator to get the precedence of.
     * @return the precedence of the operator.
     */
    private static int getPrecedence(String operator) {
        return switch (operator) {
            case "+", "-" ->
                1;
            case "*", "/" ->
                2;
            default ->
                -1;
        };
    }

    /**
     * Returns true if the first operator has higher or equal precedence than the
     * second operator.
     * 
     * @param op1 the first operator.
     * @param op2 the second operator.
     * @return true if the first operator has higher or equal precedence than the
     *         second operator.
     */
    private static boolean hasHigherOrEqualPrecedence(String op1, String op2) {
        return getPrecedence(op1) >= getPrecedence(op2);
    }

    /**
     * Returns true if the given token is an operator.
     * 
     * @param token the token to check.
     * @return true if the given token is an operator.
     */
    private static boolean isOperator(String token) {
        return "+-*/".contains(token) && token.length() == 1;
    }

    private static boolean isNumeric(String str) {
        try {
            Double.valueOf(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Applies the given operator to the given operands.
     * 
     * @param a        the first operand.
     * @param b        the second operand.
     * @param operator the operator to apply.
     * @return the result of the operation.
     */
    private static double applyOperator(double a, double b, String operator) {
        switch (operator) {
            case "+" -> {
                return a + b;
            }
            case "-" -> {
                return a - b;
            }
            case "*" -> {
                return a * b;
            }
            case "/" -> {
                if (b == 0) {
                    throw new ArithmeticException("Division by zero.");
                }
                return a / b;
            }
            default ->
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}
