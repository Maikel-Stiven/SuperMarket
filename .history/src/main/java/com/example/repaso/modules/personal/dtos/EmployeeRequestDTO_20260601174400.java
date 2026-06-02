package com.example.repaso.modules.personal.dtos;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class EmployeeRequestDTO {
    
    @NotBlank (message = "Por favor ingrese su cédula")
    @Size ()
}
