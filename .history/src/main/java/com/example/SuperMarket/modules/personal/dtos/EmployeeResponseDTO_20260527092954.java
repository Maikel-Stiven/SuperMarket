package com.example.SuperMarket.modules.personal.dtos;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class EmployeeResponseDTO {
    private String id;
    private String nombre;
    private String rol;
    private LocalDate fechaContratacion;
    private Double salario;    
}
