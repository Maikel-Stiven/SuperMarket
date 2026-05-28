package com.example.SuperMarket.modules.inventory.services;

import com.example.SuperMarket.modules.inventory.dtos.CategoryDTO;
import com.example.SuperMarket.modules.inventory.entities.Category;
import com.example.SuperMarket.modules.inventory.repositories.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        if (categoryRepository.existsByNombre(categoryDTO.getNombre())) {
            throw new IllegalArgumentException("Ya existe una categoría con el mismo nombre" + categoryDTO.getNombre());
        }

        Category categoria = new Category()

}
