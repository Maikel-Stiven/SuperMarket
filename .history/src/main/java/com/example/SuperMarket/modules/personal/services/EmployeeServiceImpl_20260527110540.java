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
        
        if (employeeRepository.existsById(request.getId())){
            throw new RuntimeException("Ya existe un empleado con el mismo documento de identidad");
        }

        Employee empleado = Employee.builder()
                .id(request.getId())
                .nombre(request.getNombre())
                .rol(Role.valueOf(request.getRol().toUpperCase()))
                .fechaContratacion(request.getFechaContratacion())
                .salario(request.getSalario())
                .build();
        
        Employee empleadoGuardado = employeeRepository.save(empleado);
        return mapToResponseDTO(empleadoGuardado);       
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeResponseDTO getEmployeeById (String id){
        Employee empleado = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado registrado con el documento de identidad número: " + id));
        return mapToResponseDTO(empleado);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeResponseDTO> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional (readOnly = true)
    public List<EmployeeResponseDTO> getEmployeesByRole(String role) {
        try{
            Role rol = Role.valueOf(role.toUpperCase());
            return employeeRepository.findByRol(rol).stream()
                    .map(this::mapToResposeDTO)
                    .collect(Collectors.toList());           
        }catch (IllegalArgumentException excepcion){
            throw new RuntimeExcepion("El cargo '" + role + "'no es valido");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeREsponseDTO> getEmployeesByHireDateRange(LocalDate startDate, LocalDate endDate){
        if (startDate.isAfter(endDate)){
            throw new runtimeException("La fecha de inicio de jornada no puede ser posterior a la fecha de fin de jornada");
        }

        return employeeRepository.findByHireDateBetween(LocalDate startDate, LocalDate endDate){
            
        }
    }


    
}
