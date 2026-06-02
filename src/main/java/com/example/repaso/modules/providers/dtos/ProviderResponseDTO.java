package com.example.repaso.modules.providers.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProviderResponseDTO {
    private Long id;
    private String nombre;
    private String nit;
    private String telefono;
    private String direccion;
    private Boolean activo;
}
