package com.example.SuperMarket.modules.inventory.repositories;

import com.example.SuperMarket.modules.inventory.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByCodigoBarras(String codigoBarras);

    Optional<Product> findByIdAndActivoTrue(Long id);

    List<Product> findByActivoTrue();

    Optional<Product> findByCodigoBarrasAndActivoTrue(String codigoBarras);

    List<Product> findByCategoriaIdAndActivoTrue(Long categoriaId);
}