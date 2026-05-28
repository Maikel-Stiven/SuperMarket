package com.example.SuperMarket.modules.providers.services;

import com.example.SuperMarket.modules.inventory.entities.Product;
import com.example.SuperMarket.modules.inventory.repositories.ProductRepository;
import com.example.SuperMarket.modules.providers.dtos.InventoryEntryDTO;
import com.example.SuperMarket.modules.providers.entities.Provider;
import com.example.SuperMarket.modules.providers.repositories.ProviderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProviderService {
    
    private final ProviderRepository providerRepository;
    private final ProductRepository productRepository;

    public ProviderService (ProviderRepository providerRepository, ProductRepository productRepository){
        this.providerRepository = providerRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public Product registroEntradaInventario(InventoryEntryDTO dto){
        Provider proveedor = providerRepository.findById(dto.getProveedorId())
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con ID: " + dto.getProveedorId()));
        
        Product producto = productRepository.findById(dto.getProductoId())
                .orElseThrow(() -> new RuntimeException("Prodcuto no encontrado con ID: " + dto.getProductoId()));
        if (dto.getCantidad() == null || dto.getCantidad() <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser un número positivo o mayor a cero.");
        }
        
        producto.setStock(producto.getStock() +dto.getCantidad());
        return productRepository.save(producto);

    }
}
