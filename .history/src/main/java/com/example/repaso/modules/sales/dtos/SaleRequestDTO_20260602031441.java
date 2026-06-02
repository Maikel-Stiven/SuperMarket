package com.example.repaso.modules.sales.dtos;

import jakarta.validation.constraints.*;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleRequestDTO {

    @NotNull(message = "El ID del empleado es obligatorio")
    private Long empleadoId;

    @NotEmpty(message = "La venta debe contener al menos 1 producto")
    private List<SaleDetailRequestDTO> detalles;
}