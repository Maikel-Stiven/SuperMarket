package com.example.SuperMarket.modules.personal.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

import org.springframework.stereotype.Controller;

@Entity
@Table(name = "Empleados")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Employee {
    
    @Id
    @Column(length = 20, nullable = false)

    private String id;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private Role rol;

    @Column(name = "hire_date", nullable = false)
    private LocalDate fechaContratacion;

    @Column(nullable = false)
    private double salario;
}
