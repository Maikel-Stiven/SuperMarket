package com.example.repaso.modules.inventory.services;

import com.example.repaso.modules.inventory.dtos.CategoryRequestDTO;
import com.example.repaso.modules.inventory.entities.Category;
import com.example.repaso.modules.inventory.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category createCategory(CategoryRequestDTO request) {
        if (categoryRepository.findByNombre(request.getNombre()).isPresent()) {
            throw new RuntimeException("Ya existe una categoría registrada con ese nombre");
        }

        Category category = new Category();
        category.setNombre(request.getNombre());
        category.setDescripcion(request.getDescripcion());

        return categoryRepository.save(category);
    }
    
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}