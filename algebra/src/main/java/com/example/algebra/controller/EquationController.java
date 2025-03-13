package com.example.algebra.controller;

import com.example.algebra.model.Equation;
import com.example.algebra.services.EquationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/equations")
public class EquationController {
    private final EquationService equationService;

    public EquationController(EquationService equationService) {
        this.equationService = equationService;
    }

    // Endpoint to store an algebraic equation
    @PostMapping("/store")
    public ResponseEntity<Map<String, String>> storeEquation(@RequestBody Map<String, String> request) {
        String equationId = equationService.storeEquation(request.get("equation"));
        return ResponseEntity.ok(Map.of("message", "Equation stored successfully", "equationId", equationId));
    }

    // Endpoint to retrieve all stored equations
    @GetMapping
    public ResponseEntity<Map<String, Equation>> getAllEquations() {
        return ResponseEntity.ok(equationService.getAllEquations());
    }

    // Endpoint to evaluate a specific equation
    @PostMapping("/{equationId}/evaluate")
    public ResponseEntity<Map<String, Object>> evaluateEquation(@PathVariable String equationId, @RequestBody Map<String, Double> variables) {
        double result = equationService.evaluateEquation(equationId, variables);
        Equation equation = equationService.getAllEquations().get(equationId);
        return ResponseEntity.ok(Map.of(
                "equationId", equationId,
                "equation", equation.getEquation(),
                "variables", variables,
                "result", result
        ));
    }
}