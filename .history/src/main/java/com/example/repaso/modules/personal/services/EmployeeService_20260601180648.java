package com.example.repaso.modules.personal.services;

import com.example.repaso.modules.personal.dtos.EmployeeResponseDTO;
import com.example.repaso.modules.personal.dtos.EmployeeRequestDTO;
import com.example.repaso.modules.personal.entities.Role;
import com.example.repaso.modules.personal.entities.Employee;
import com.example.repaso.modules.personal.repositories.EmployeeRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.List;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class EmployeeService{
    
    private final EmployeeRepository employeeRepository;

    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO request){
        Employee empleado = new Employee();
        empleado.setCedula(request.getId());
        empleado.setNombre(request.getNombre());
        empleado.setRol(request.getRol());
        empleado.setSalario(request.getSalario());
        
    }
}
