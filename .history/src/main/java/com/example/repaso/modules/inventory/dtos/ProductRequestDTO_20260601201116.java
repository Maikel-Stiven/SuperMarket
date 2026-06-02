package com.example.repaso.modules.inventory.dtos;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductRequestDTO {
    
    @NotBlank(message = "El codigo de barras es obligatorio")
    private String codigoBarras;

    @NotBlank(message = "El nombre del producto no puede estar vacio")
    private String nombre;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio no puede ser un número negativo")
    private Double precio;

    @NotNull(message = "El stock inicial es obligatorio")
    @Min(value = 0, message = "El stock no puede estar en negativos")
    private Integer stock;

    @
}
