package com.example.repaso.modules.personal.dtos;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class EmployeeRequestDTO {
    
    @NotBlank (message = "Por favor ingrese su cédula")
    @Size (min = 6, max = 20, message = "La cédula debe tener entre 6 a 20 caracteres")
    private String id;
    
    @NotBlank (message = "Por favor ingrese su nombre completo")
    @Size (min = 3, max = 100, message = "El nombre debe tener")
    private String nombre;
}
