package com.example.SuperMarket.modules.inventory.dtos;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ProductResponseDTO {
    private Long id;
    private String codigoBarras;
    private String nombre;
    private Double precio;
    private Integer stock;
    private Boolean activo;
    private CategoryDTO categoria;    
}
