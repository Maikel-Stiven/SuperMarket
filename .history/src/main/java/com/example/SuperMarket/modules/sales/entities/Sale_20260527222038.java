package com.example.SuperMarket.modules.sales.entities;

import com.example.SuperMarket.modules.personal.entities.Employee;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ventas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sale {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column(nullable = false, precision = 10, scale = 2)
    private Double subtotal;

    @Column(nullable = false, precision = 10, scale = 2)
    private Double iva;

    @Column(nullable = false, precision = 10, scale = 2)
    private Double total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empleado_id", nullable = false)
    private Employee empleado;

    


}
