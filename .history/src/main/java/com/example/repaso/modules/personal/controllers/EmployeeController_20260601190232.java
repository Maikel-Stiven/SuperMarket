package com.example.repaso.modules.personal.controllers;

import com.example.repaso.modules.personal.dtos.EmployeeRequestDTO;
import com.example.repaso.modules.personal.dtos.EmployeeResponseDTO;
import com.example.repaso.modules.personal.services.EmployeeService;
import com.example.repaso.modules.personal.entities.Role;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/empleados")
@RequiredArgsConstructor
public class EmployeeController {
    
    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> createEmployee(@Valid @RequestBody EmployeeReuestDTO request){
        employeeService.createEmployee(request);
        return new ResponseEntity<>()
    }
}
