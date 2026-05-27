package com.example.SuperMarket.modules.personal.dtos;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class EmployeeRequestDTO {
    
    @NotBlank (message = "Por favor ingrese su documento de identidad")
    @Size(min = 6, max = 20, message = "El documento de identidad debe tener entre 6 y 20 caracteres")
    private String id;

    @NotBlank (message = "Por favor ingrese su nombre completo")
    @Size(min = 2, max = 100, message = "El nombre no puede superar los 100 caracteres")
    private String nombre;

    @NotBlank (message = "el cargo es obligatorio")
    @Pattern(regexp = "GERENTE|CAJERO|AUXILIAR", message = "El cargo debe ser 'GERENTE', 'CAJERO' o 'AUXILIAR'")
    private String rol;

    @NotNull (message = "La fecha de ingreso es obligatoria")
    @PastOrPresent (message = "La fecha de ingreso no puede ser futura") 
    private LocalDate fechaContratacion;

    @NotNull (message = "El salario es obligatorio")
    @Positive (message = "El salario debe ser un número positivo")
    private Double salario;
}
