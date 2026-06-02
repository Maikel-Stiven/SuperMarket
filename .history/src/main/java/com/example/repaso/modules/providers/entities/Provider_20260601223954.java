package com.example.repaso.modules.providers.entities;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "Proveedores")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Provider {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable =  false, unique = true , length = 80)
    private String nit;

    @Column(length = 20)
    private String telefono;

    @Column(length = 200)
    private String direccion;

    @Column(nullable =  false)
    private Boolean activo;
}
