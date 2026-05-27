package com.example.SuperMarket.modules.inventory.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CategoryDTO {

    private Long id;

    @NotBlank(message = "El nombre de la categoría es obligatorio")
    @Size(max = 50, message = "El nombre de la categoría no puede exceder los 50 caracteres")
    private String nombre;

    @Size(max = 225, message = "La descripción de la categoría no puede exceder los 225 caracteres")
    private String descripcion;
    
}
