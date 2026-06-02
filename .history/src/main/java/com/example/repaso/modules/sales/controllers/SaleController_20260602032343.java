package com.example.repaso.modules.sales.controllers;

import com.example.repaso.modules.sales.dtos.SaleRequestDTO;
import com.example.repaso.modules.sales.dtos.SaleResponseDTO;
import com.example.repaso.modules.sales.services.SaleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Ventas")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;

    @PostMapping
    public ResponseEntity<SaleResponseDTO> checkout(@Valid @RequestBody SaleRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(saleService.createSale(request));
    }
}