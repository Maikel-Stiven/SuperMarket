package com.example.SuperMarket.modules.sales.repositories;

import com.example.SuperMarket.modules.sales.entities.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleDetailRepository extends JpaRepository<SaleDetail, Long> {
    
}
