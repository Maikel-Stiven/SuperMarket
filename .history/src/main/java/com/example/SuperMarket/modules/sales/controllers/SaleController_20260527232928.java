package com.example.SuperMarket.modules.sales.controllers;

import com.example.SuperMarket.modules.sales.dtos.CreateSaleDTO;
import com.example.SuperMarket.modules.sales.entities.Sale;
import com.example.SuperMarket.modules.sales.services.SaleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sales")

public class SaleController {
    
    private final SaleService saleService;

    public SaleController(SaleService saleService){
        this.saleService = saleService;
    }

    @PostMapping
    public ResponseEntity<?> crearVenta(@RequestBody CreateSaleDTO dto){
        try{
            Sale nuevaVenta = saleService.registrarVenta(dto)
            return ResponseEntity.ok(nuevaVenta);
        }catch (Exception excepcion){
            return ResponseEntity.badRequest().body(excepcion.getMessage());
        }
    }
}
