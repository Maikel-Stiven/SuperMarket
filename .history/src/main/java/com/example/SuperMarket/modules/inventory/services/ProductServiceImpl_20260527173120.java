package com.example.SuperMarket.modules.inventory.services;

import com.example.SuperMarket.modules.inventory.dtos.ProductRequestDTO;
import com.example.SuperMarket.modules.inventory.dtos.ProductResponseDTO;
import com.example.SuperMarket.modules.inventory.entities.Category;
import com.example.SuperMarket.modules.inventory.entities.Product;
import com.example.SuperMarket.modules.inventory.repositories.CategoryRepository;
import com.example.SuperMarket.modules.inventory.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public ProductResponseDTO createProduct(ProductRequestDTO request) {
        if (productRepository.existsByCodigoBarras(request.getCodigoBarras())){
            throw new RuntimeException()
        }
    }
}
