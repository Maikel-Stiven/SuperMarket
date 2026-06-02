package com.example.repaso.modules.personal.dtos;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

import javax.management.relation.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class EmployeeRequestDTO {
    
    @NotBlank (message = "Por favor ingrese su cédula")
    @Size (min = 6, max = 20, message = "La cédula debe tener entre 6 a 20 caracteres")
    private String id;
    
    @NotBlank (message = "Por favor ingrese su nombre completo")
    @Size (min = 3, max = 100, message = "El nombre no debería sobrepasar los 100 caracteres")
    private String nombre;
    
    @NotBlank(message = "Ingrese su cargo | recuerde que  el cargo es obligatorio")
    @Pattern (regexp = "ADMINISTRADOR|CAJERO|AUXILIAR", message = "No existe el cargo que ha escrito")
    private Role rol;
    
    @NotNull (message = "La fecha de ingreso  es obligatoria")
    @PastOrPresent (message = "La fecha no puede ser futura")
    private LocalDate fechaIngreso;

    @NotNull(message = "El salario es obligatorio")
    @Positive(message = "El salario debe ser un número positivo")
    private Double salario;
}
