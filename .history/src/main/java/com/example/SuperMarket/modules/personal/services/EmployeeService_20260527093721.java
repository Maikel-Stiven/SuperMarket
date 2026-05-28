package com.example.SuperMarket.modules.personal.services;

import com.example.SuperMarket.modules.personal.dtos.EmployeeRequestDTO;
import com.example.SuperMarket.modules.personal.dtos.EmployeeResponseDTO;

import java.util.List;
import java.time.LocalDate;

public interface EmployeeService {
    
    EmployeeResponseDTO createEmployee (EmployeeRequestDTO request);
    EmployeeResponseDTO getEmployeeById (String id);
    List<EmployeeResponseDTO> getAllEmployees();
    List<EmployeeResponseDTO> getEmployeesByRole (String rol);
    List<EmployeeResponseDTO> getEmployeeByHiredDateRange (LocalDate startDate, LocalDate endDate);
}
