package com.example.SuperMarket.modules.personal.controller;

import com.example.SuperMarket.modules.personal.dtos.EmployeeRequestDTO;
import com.example.SuperMarket.modules.personal.dtos.EmployeeResponseDTO;
import com.example.SuperMarket.modules.personal.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/api/v1/empleados")
public class EmployeeController {
    
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> createEmployee(@Valid @RequestBody EmployeeRequestDTO request){
        EmployeeResponseDTO response = employeeService.createEmployee(request);
}
