package com.example.SuperMarket.modules.inventory.controllers;

import com.example.SuperMarket.modules.inventory.dtos.ProductRequestDTO;
import com.example.SuperMarket.modules.inventory.dtos.ProductResponseDTO;
import com.example.SuperMarket.modules.inventory.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody ProductRequestDTO request) {
        return new ResponseEntity<>(productService.createProduct(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping("/barcode/{codigoBarras}")
    public ResponseEntity<ProductResponseDTO> getProductByCodigoBarras(@PathVariable String codigoBarras) {
        return ResponseEntity.ok(productService.getProductByCodigoBarras(codigoBarras));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/category/{categoriaId}")
    public ResponseEntity<List<ProductResponseDTO>> getProductsByCategoriaId(@PathVariable Long categoriaId) {
        return ResponseEntity.ok(productService.getProductsByCategoriaId(categoriaId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(
            @PathVariable Long id, 
            @Valid @RequestBody ProductRequestDTO request) {
        return ResponseEntity.ok(productService.updateProduct(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductLogically(@PathVariable Long id) {
        productService.deleteProductLogically(id);
        return ResponseEntity.noContent().build();
    }
}