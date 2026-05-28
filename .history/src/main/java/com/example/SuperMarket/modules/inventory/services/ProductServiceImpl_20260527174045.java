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
        return mapToDTO(productoCreado);        

    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponseDTO getProductById(Long id) {
        Product product = productRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new RuntimeException("No se encontró ningún producto activo con el ID: " + id));
        return mapToResponseDTO(product);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponseDTO getProductByCodigoBarras(String codigoBarras) {
        Product product = productRepository.findByCodigoBarrasAndActiveTrue(codigoBarras)
                .orElseThrow(() -> new RuntimeException("No se encontró ningún producto activo con el código: " + codigoBarras));
        return mapToResponseDTO(product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponseDTO> getAllActiveProducts() {
        return productRepository.findByActiveTrue().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponseDTO> getProductsByCategory(Long categoryId) {
        return productRepository.findByCategoryIdAndActiveTrue(categoryId).stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO requestDTO) {
        Product product = productRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el producto o está inactivo."));

        if (!product.getCodigoBarras().equals(requestDTO.getCodigoBarras()) && productRepository.existsByCodigoBarras(requestDTO.getCodigoBarras())) {
            throw new RuntimeException("El código de barras '" + requestDTO.getCodigoBarras() + "' ya pertenece a otro producto.");
        }

        Category category = categoryRepository.findById(requestDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("La categoría seleccionada no existe."));

        product.setCodigoBarras(requestDTO.getCodigoBarras());
        product.setNombre(requestDTO.getNombre());
        product.setPrice(requestDTO.getPrice());
        product.setStock(requestDTO.getStock());
        product.setCategory(category);

        Product updatedProduct = productRepository.save(product);
        return mapToResponseDTO(updatedProduct);
    }

    @Override
    @Transactional
    public void deleteProductLogically(Long id) {
        Product product = productRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el producto para eliminar o ya se encuentra inactivo."));
        
        // BORRADO LÓGICO: Modificamos el estado en lugar de hacer un delete físico
        product.setActive(false);
        productRepository.save(product);
    }

    private ProductResponseDTO mapToResponseDTO(Product product) {
        return ProductResponseDTO.builder()
                .id(product.getId())
                .codigoBarras(product.getCodigoBarras())
                .nombre(product.getNombre())
                .precio(product.getPrecio())
                .stock(product.getStock())
                .categoria(product.getCategoria().getId())
                .ca
                .build();
    }
}
