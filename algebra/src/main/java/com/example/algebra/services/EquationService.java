package com.example.algebra.services;

import com.example.algebra.model.Equation;
import com.example.algebra.model.PostfixTree;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

@Service
public class EquationService {
    private final Map<String, Equation> equations = new HashMap<>();
    private int idCounter = 1;

    // Method to store an equation
    public String storeEquation(String equationStr) {
        PostfixTree tree = new PostfixTree();
        String postfix = convertToPostfix(equationStr); // Convert to postfix notation
        tree.buildTree(postfix); // Build the tree from postfix
        String equationId = String.valueOf(idCounter++);
        equations.put(equationId, new Equation(equationId, equationStr));
        return equationId;
    }

    // Method to retrieve all stored equations
    public Map<String, Equation> getAllEquations() {
        return equations;
    }

    // Method to evaluate a specific equation
    public double evaluateEquation(String equationId, Map<String, Double> variables) {
        Equation equation = equations.get(equationId);
        if (equation != null) {
            PostfixTree tree = new PostfixTree();
            String postfix = convertToPostfix(equation.getEquation());
            tree.buildTree(postfix);
            return tree.evaluate(variables.getOrDefault("x", 0.0),
                                 variables.getOrDefault("y", 0.0),
                                 variables.getOrDefault("z", 0.0));
        }
        throw new IllegalArgumentException("Equation not found");
    }

    // Method to convert infix expression to postfix
    private String convertToPostfix(String infix) {
        StringBuilder postfix = new StringBuilder();
        Stack<String> stack = new Stack<>();
        String[] tokens = infix.split(" ");

        for (String token : tokens) {
            if (isNumeric(token) || isVariable(token)) {
                postfix.append(token).append(" ");
            } else if (token.equals("(")) {
                stack.push(token);
            } else if (token.equals(")")) {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    postfix.append(stack.pop()).append(" ");
                }
                stack.pop(); // Remove the '(' from the stack
            } else { // Operator
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(token)) {
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

    // Helper method to check if a token is numeric
    private boolean isNumeric(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Helper method to check if a token is a variable
    private boolean isVariable(String token) {
        return token.matches("[a-zA-Z]+"); // Matches single-letter variables (e.g., x, y, z)
    }

    // Helper method to determine the precedence of operators
    private int precedence(String operator) {
        switch (operator) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            case "^":
                return 3;
            default:
                return 0;
        }
    }
}