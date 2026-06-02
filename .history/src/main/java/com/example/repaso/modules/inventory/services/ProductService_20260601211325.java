package com.example.repaso.modules.inventory.services;

import com.example.repaso.modules.inventory.entities.Product;
import com.example.repaso.modules.inventory.repositories.CategoryRepository;
import com.example.repaso.modules.inventory.repositories.ProductRepository;

import lombok.RequiredArgsConstructor;

import com.example.repaso.modules.inventory.dtos.ProductRequestDTO;
import com.example.repaso.modules.inventory.dtos.ProductResponseDTO;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductResponseDTO createProduct (ProductRequestDTO request){

        if (productRepository.findBycodigoBarras(request.getCodigoBarras()).isPresent()) {
            throw new RuntimeException("El código de barras ya existe");
        }
        if (!categoryRepository.existsById(request.getCategoriaId())) {
            throw new RuntimeException("La categoría no existe");
        }

        Product producto = new Product();
        producto.setCodigoBarras(request.getCodigoBarras());
        producto.setNombre(request.getNombre());
        producto.setPrecio(request.getPrecio());
        producto.setStock(request.getStock());
        producto.setCategoriaId(request.getCategoriaId());
        producto.setActivo(true);

        return mapToResponse(productRepository.save(producto));
    }

    public List<ProductResponseDTO> getActiveProducts(){
        return productRepository.findByActivoTrue().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public void deleteProduct(Long id){
        Product producto = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El producto no existe"));

        producto.setActivo(false);
        productRepository.save(producto);        
    }

    private ProductResponseDTO mapToResponse(Product prod){
        return new ProductResponseDTO(
            prod.getId(),
            prod.getCodigoBarras(),
            prod.getNombre(),
            prod.getPrecio(),
            prod.getStock(),
            prod.getCategoriaId(),
            prod.getActivo()
        );
    }
}
