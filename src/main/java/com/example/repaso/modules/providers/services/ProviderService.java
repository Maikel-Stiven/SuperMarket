package com.example.repaso.modules.providers.services;

import com.example.repaso.modules.providers.dtos.ProviderRequestDTO;
import com.example.repaso.modules.providers.dtos.ProviderResponseDTO;
import com.example.repaso.modules.providers.entities.Provider;
import com.example.repaso.modules.providers.repositories.ProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProviderService {

    private final ProviderRepository providerRepository;

    public ProviderResponseDTO createProvider(ProviderRequestDTO request) {
        if (providerRepository.findByNit(request.getNit()).isPresent()) {
            throw new RuntimeException("El NIT ya se encuentra registrado por otro proveedor");
        }

        Provider proveedor = new Provider();
        proveedor.setNombre(request.getNombre());
        proveedor.setNit(request.getNit());
        proveedor.setTelefono(request.getTelefono());
        proveedor.setDireccion(request.getDireccion());
        proveedor.setActivo(true);

        return mapToResponse(providerRepository.save(proveedor));
    }

    public List<ProviderResponseDTO> getActiveProviders() {
        return providerRepository.findByActivoTrue().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public void deleteProviderLogically(Long id) {
        Provider proveedor = providerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El proveedor no existe"));
        
        proveedor.setActivo(false); // Apagamos el estado
        providerRepository.save(proveedor);
    }

    private ProviderResponseDTO mapToResponse(Provider prov) {
        ProviderResponseDTO response = new ProviderResponseDTO();
        response.setId(prov.getId());
        response.setNombre(prov.getNombre());
        response.setNit(prov.getNit());
        response.setTelefono(prov.getTelefono());
        response.setDireccion(prov.getDireccion());
        response.setActivo(prov.getActivo());
        return response;
    }
}
