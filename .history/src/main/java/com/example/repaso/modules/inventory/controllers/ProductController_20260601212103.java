package com.example.repaso.modules.inventory.controllers;

import com.example.repaso.modules.inventory.services.ProductService;
import com.example.repaso.modules.inventory.dtos.ProductRequestDTO;
import com.example.repaso.modules.inventory.dtos.ProductResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Productos")
@RequiredArgsConstructor
public class ProductController {
    
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDTO> create(@Valid)
}
