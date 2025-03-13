package com.example.algebra.model;

import java.util.Stack;

public class PostfixTree {
    private static class TreeNode {
        String value;
        TreeNode left;
        TreeNode right;

        TreeNode(String value) {
            this.value = value;
        }
    }

    private TreeNode root;

    public void buildTree(String postfix) {
        Stack<TreeNode> stack = new Stack<>();
        String[] tokens = postfix.split(" ");
        
        for (String token : tokens) {
            if (isOperator(token)) {
                TreeNode node = new TreeNode(token);
                node.right = stack.pop();
                node.left = stack.pop();
                stack.push(node);
            } else {
                stack.push(new TreeNode(token));
            }
        }
        root = stack.pop();
    }

    public double evaluate(double x, double y, double z) {
        return evaluate(root, x, y, z);
    }

    private double evaluate(TreeNode node, double x, double y, double z) {
        if (node == null) {
            return 0;
        }
        if (isOperator(node.value)) {
            double left = evaluate(node.left, x, y, z);
            double right = evaluate(node.right, x, y, z);
            return applyOperator(node.value, left, right);
        } else {
            return getValue(node.value, x, y, z);
        }
    }

    private boolean isOperator(String token) {
        return "+-*/^".contains(token);
    }

    private double applyOperator(String operator, double left, double right) {
        switch (operator) {
            case "+":
                return left + right;
            case "-":
                return left - right;
            case "*":
                return left * right;
            case "/":
                return left / right;
            case "^":
                return Math.pow(left, right);
            default:
                throw new UnsupportedOperationException("Unknown operator: " + operator);
        }
    }

    private double getValue(String variable, double x, double y, double z) {
        switch (variable) {
            case "x":
                return x;
            case "y":
                return y;
            case "z":
                return z;
            default:
                return Double.parseDouble(variable);
        }
    }
}