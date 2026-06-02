package com.example.repaso.modules.inventory.repositories;

import com.example.repaso.modules.inventory.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
