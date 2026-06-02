package com.example.repaso.modules.providers.controllers;

import com.example.repaso.modules.providers.dtos.ProviderRequestDTO;
import com.example.repaso.modules.providers.dtos.ProviderResponseDTO;
import com.example.repaso.modules.providers.services.ProviderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
@RequiredArgsConstructor
public class ProviderController {

    private final ProviderService providerService;

    @PostMapping
    public ResponseEntity<ProviderResponseDTO> create(@Valid @RequestBody ProviderRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(providerService.createProvider(request));
    }

    @GetMapping
    public ResponseEntity<List<ProviderResponseDTO>> getAllActive() {
        return ResponseEntity.ok(providerService.getActiveProviders());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLogically(@PathVariable Long id) {
        providerService.deleteProviderLogically(id);
        return ResponseEntity.noContent().build();
    }
}