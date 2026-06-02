package com.example.repaso.modules.sales.repositories;

import com.example.repaso.modules.sales.entities.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SaleDetailRepository extends JpaRepository<SaleDetail, Long> {
    List<SaleDetail> findByVentaId(Long ventaId);
}