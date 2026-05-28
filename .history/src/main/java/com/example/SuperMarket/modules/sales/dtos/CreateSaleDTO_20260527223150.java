package com.example.SuperMarket.modules.sales.dtos;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class CreateSaleDTO {
    private Long empleadoId;
    private List<SaleItemDTO> items;
}
