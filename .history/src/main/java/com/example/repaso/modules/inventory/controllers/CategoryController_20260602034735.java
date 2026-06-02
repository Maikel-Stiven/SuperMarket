package com.example.repaso.modules.inventory.controllers;

import com.example.repaso.modules.inventory.dtos.CategoryRequestDTO;
import com.example.repaso.modules.inventory.entities.Category;
import com.example.repaso.modules.inventory.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Categorias")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    
    @PostMapping
    public ResponseEntity<Category> create(@Valid @RequestBody CategoryRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(request));
    }
    
    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
}