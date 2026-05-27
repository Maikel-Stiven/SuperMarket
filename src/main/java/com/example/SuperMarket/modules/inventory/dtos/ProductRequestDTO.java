package com.example.SuperMarket.modules.inventory.dtos;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ProductRequestDTO {
    
    @NotBlank(message = "El código de barras debe ser obligatorio")
    @Size(max = 50, message = "El código de barras no puede exceder los 50 caracteres")
    private String codigoBarras;

    @NotBlank(message = "El nombre del producto es obligatorio")
    @Size(max = 100, message = "El nombre del producto no puede exceder los 100 caracteres")
    private String nombre;

    @NotNull(message = "El precio del producto es obligatorio")
    @Positive(message = "El precio del producto debe ser un valor positivo")
    private Double precio;

    @NotNull(message = "El stock del producto es obligatorio")
    @Min(value = 0, message = "El stock del producto no puede ser negativo")
    private Integer stock;

    @NotNull(message = "El ID de la categoría debe ser asignado")
    private Long categoriaId;
}


