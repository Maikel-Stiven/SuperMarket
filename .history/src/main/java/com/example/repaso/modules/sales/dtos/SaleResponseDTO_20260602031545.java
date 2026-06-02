package com.example.repaso.modules.sales.dtos;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleResponseDTO {
    private Long id;
    private LocalDateTime fecha;
    private Double total;
    private Long idEmpleado;
}