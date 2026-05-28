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
    }
}
