package com.example.repaso.modules.inventory.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseDTO {
    private Long id;
    private String nombre;
    private String descripcion;
}