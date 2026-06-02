package com.example.repaso.modules.sales.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Detalles_venta")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class SaleDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "venta_id", nullable = false)
    private Long ventaId; 

    @Column(name = "producto_id", nullable = false)
    private Long productoId;
     
    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false)
    private Double precioUnitario;

    @Column(nullable = false)
    private Double subtotal;
}