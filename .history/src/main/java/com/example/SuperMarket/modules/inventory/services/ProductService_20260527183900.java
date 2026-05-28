package com.example.SuperMarket.modules.inventory.services;

import com.example.SuperMarket.modules.inventory.dtos.ProductRequestDTO;
import com.example.SuperMarket.modules.inventory.dtos.ProductResponseDTO;
import java.util.List;

public interface ProductService {
    ProductResponseDTO createProduct(ProductRequestDTO request);
    ProductResponseDTO getProductById(Long id);
    ProductResponseDTO getProductByCodigoBarras(String codigoBarras);
    List<ProductResponseDTO> getAllActiveProducts();
    List<ProductResponseDTO> getAllProducts();
    List<ProductResponseDTO> getProductsByCategoriaId(Long categoriaId);
    ProductResponseDTO updateProduct(Long id, ProductRequestDTO request);
    void deleteProductLogically(Long id);
}