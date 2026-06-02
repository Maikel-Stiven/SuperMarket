package com.example.repaso.modules.inventory.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDTO {
    private Long id;
    private String codigoBarras;
    private String nombre;
    private Double precio;
    private Integer stock;
    private Long categoriaId;
    private Boolean activo;
}
