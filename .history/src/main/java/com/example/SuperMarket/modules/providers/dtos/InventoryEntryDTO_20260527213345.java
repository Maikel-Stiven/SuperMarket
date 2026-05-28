package com.example.SuperMarket.modules.providers.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryEntryDTO {
    private Long productoId;
    private Long proveedorId;
    private Integer cantidad;
}
