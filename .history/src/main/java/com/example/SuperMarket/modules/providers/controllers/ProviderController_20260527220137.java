package com.example.SuperMarket.modules.providers.controllers;

import com.example.SuperMarket.modules.inventory.entities.Product;
import com.example.SuperMarket.modules.providers.dtos.InventoryEntryDTO;
import com.example.SuperMarket.modules.providers.services.ProviderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/providers")
public class ProviderController {
    
    private final ProviderService providerService;

    public ProviderController(ProviderService providerService){
        this.providerService = providerService;
    }

    @PostMapping("/entrada")
    public ResponseEntity<?> registrarEntrada(@RequestBody InventoryEntryDTO dto){
        try{
            Product productoActualizado = providerService.registroEntradaInventario(dto);
            return ResposeEntity.ok("Stock actualizado exitosamente. Stock nuevo: "
                    + productoActualizado.getStock()
            )
        }
    }
}
