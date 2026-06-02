package com.example.repaso.modules.providers.repositories;

import com.example.repaso.modules.providers.entities.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;


@Repository
public interface ProviderRepository extends JpaRepository <Provider, Long> {
    
    Optional<Provider> findByNit(String nit);
    List<Provider> findByActivoTrue();
    
}
