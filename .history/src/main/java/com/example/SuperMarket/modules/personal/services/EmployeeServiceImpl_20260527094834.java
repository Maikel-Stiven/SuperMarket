package com.example.SuperMarket.modules.personal.services;

import com.example.SuperMarket.modules.personal.dtos.EmployeeRequestDTO;
import com.example.SuperMarket.modules.personal.dtos.EmployeeResponseDTO;
import com.example.SuperMarket.modules.personal.entities.Employee;
import com.example.SuperMarket.modules.personal.entities.Role;
import com.example.SuperMarket.modules.personal.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    @Transactional
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO request) {
        
        if (employeeRepository.existById(request.getId())){
            throw new RuntimeException("Ya existe un empleado con el mismo documento de identidad");
        }
    }
}
