package com.example.algebra.model;

public class Equation {
    private String equationId;
    private String equation;

    public Equation(String equationId, String equation) {
        this.equationId = equationId;
        this.equation = equation;
    }

    public String getEquationId() {
        return equationId;
    }

    public void setEquationId(String equationId) {
        this.equationId = equationId;
    }

    public String getEquation() {
        return equation;
    }

    public void setEquation(String equation) {
        this.equation = equation;
    }
}