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
            throw new RuntimeException("El código de barras" + request.getCodigoBarras() + "ya esta registrado en el sistema");
        }

        Category categoria = categoryRepository.findById(request.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("La categoria asignada no existe"));
        
        Product producto = Product.builder()
                .codigoBarras(request.getCodigoBarras())
                .nombre(request.getNombre())
                .precio(request.getPrecio())
                .stock(request.getStock())
                .activo(true)
                .categoria(categoria)
                .build();
                
        Product productoCreado = productRepository.save(producto);
        return mapToResponseDTO(productoCreado);        

    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponseDTO getProductById(Long id) {
        Product producto = productRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con el código: " + id));
        return mapToResponseDTO(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponseDTO getProductByCodigoBarras(String codigoBarras) {
        Product producto = productRepository.findByCodigoBarrasAndActiveTrue(codigoBarras)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con el código de barras: " + codigoBarras));
        return mapToResponseDTO(producto);
    }


}
