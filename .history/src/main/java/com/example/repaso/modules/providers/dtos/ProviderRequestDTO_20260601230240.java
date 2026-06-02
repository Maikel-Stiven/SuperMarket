package com.example.repaso.modules.providers.dtos;

import jakarta.validation.constraints.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProviderRequestDTO {
    
    @NotBlank(message = "El nombre del proveedor es obligatorio")
    @Size(max = 100, message = "El nombre del proveedor no debe exceder los 100 caracteres")
    private String nombre;

    @NotBlank(message = "El NIT del proveedor es obligatorio")
    @Size(max = 80, message = "El NIT del proveedor no debe exceder los 80 caracteres")
    private String nit;

    @Size(max = 20, message = "El teléfono del proveedor no debe exceder los 20 caracteres")
    private String telefono;

    @Size(max = 200, message = "La dirección del proveedor no debe exceder los 200 caracteres")
    private String direccion;
}
