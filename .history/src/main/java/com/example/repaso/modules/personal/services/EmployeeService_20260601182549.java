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

        Object requestRol = request.getRol();
        if (requestRol instanceof Role) {
            empleado.setRol((Role) requestRol);
        } else if (requestRol instanceof javax.management.relation.Role) {
            empleado.setRol(Role.valueOf(((javax.management.relation.Role) requestRol).getRoleName()));
        } else if (requestRol != null) {
            empleado.setRol(Role.valueOf(requestRol.toString()));
        }

        empleado.setSalario(request.getSalario());
        empleado.setFechaIngreso(request.getFechaIngreso());
        empleado.setActivo(true);

        Employee empleadoCreado = employeeRepository.save(empleado);
        return mapToResponse(empleadoCreado);
    }

    public List<EmployeeResponseDTO> getAllEmployees(){
        return employeeRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<EmployeeResponseDTO> getEmployeesByRole(Role rol){
        return employeeRepository.findByRol(rol).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<EmployeeResponseDTO> getEmployeesByDateRange(LocalDate start, LocalDate end){
        return employeeRepository.findByfechaIngresoBetween(start, end).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private EmployeeResponseDTO mapToResponse(Employee empleado){
        return new EmployeeResponseDTO(
            empleado.getId(), 
            empleado.getCedula(),
            empleado.getNombre(),
            empleado.getRol(),
            empleado.getSalario(),
            empleado.getFechaIngreso(),
            empleado.isActivo()
        );
    }
}
