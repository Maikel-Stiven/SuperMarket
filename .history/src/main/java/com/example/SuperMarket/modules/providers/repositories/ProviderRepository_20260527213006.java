package com.example.SuperMarket.modules.providers.repositories;

import com.example.SuperMarket.modules.providers.entities.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {
    Optional<Provider> findByNit(String nit);    
}
