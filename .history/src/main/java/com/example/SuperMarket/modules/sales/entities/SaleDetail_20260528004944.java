package com.example.SuperMarket.modules.sales.entities;

import com.example.SuperMarket.modules.inventory.entities.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "detalles_ventas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleDetail {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false, precision = 10)
    private Double precioUnitario;

    @Column(nullable = false, precision = 10)
    private Double subtotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venta_id", nullable = false)
    @JsonBackReference
    private Sale venta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    private Product producto;

}
