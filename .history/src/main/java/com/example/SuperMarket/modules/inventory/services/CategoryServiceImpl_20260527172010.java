package com.example.SuperMarket.modules.inventory.services;

import com.example.SuperMarket.modules.inventory.dtos.CategoryDTO;
import com.example.SuperMarket.modules.inventory.dtos.ProductResponseDTO;
import com.example.SuperMarket.modules.inventory.entities.Category;
import com.example.SuperMarket.modules.inventory.repositories.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public ProductResponseDTO createProduct(ProductRequestDTO Request) {

        if (productRepository.existsByCodigoBarras(Request.getCodigoBarras())) {
          throw new RuntimeException("El código de barras '" + Request.getCodigoBarras() + "' ya existe estar registrado en el sistema.");
        }

        Category categoria = ca
    }



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

        Category categoria = Category.builder()
                .nombre(categoryDTO.getNombre())
                .descripcion(categoryDTO.getDescripcion())
                .build();

        Category categoriaActualizada = categoryRepository.save(categoria);
        return mapToDTO(categoriaActualizada);
    }
    
    @Override
    @Transactional(readOnly = true)
    public CategoryDTO getCategoryById(Long id) {
        Category categoria = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con id: " + id));
        return mapToDTO(categoria);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        Category categoria = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con id: " + id));
        if (!categoria.getNombre().equals(categoryDTO.getNombre()) && categoryRepository.existsByNombre(categoryDTO.getNombre())) {
            throw new IllegalArgumentException("Ya existe una categoría con el mismo nombre: " + categoryDTO.getNombre());
        } 

        categoria.setNombre(categoryDTO.getNombre());
        categoria.setDescripcion(categoryDTO.getDescripcion());

        Category categoriaActualizada = categoryRepository.save(categoria);
        return mapToDTO(categoriaActualizada);
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {
        Category categoria = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con id: " + id));
        categoryRepository.delete(categoria);
    }

    private CategoryDTO mapToDTO(Category categoria) {
        return CategoryDTO.builder()
                .id(categoria.getId())
                .nombre(categoria.getNombre())
                .descripcion(categoria.getDescripcion())
                .build();
    }

}
