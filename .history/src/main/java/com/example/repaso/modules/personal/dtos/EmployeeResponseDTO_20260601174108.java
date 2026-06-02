package com.example.repaso.modules.personal.dtos;

import com.example.repaso.modules.personal.entities.Role;
import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class EmployeeResponseDTO {
    private Long id;
    private String cedula;
    private String nombre;
    private Role rol;
    private Double salario;
    private LocalDate fechaIngreso;
    private Boolean activo;
}
