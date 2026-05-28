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
}
