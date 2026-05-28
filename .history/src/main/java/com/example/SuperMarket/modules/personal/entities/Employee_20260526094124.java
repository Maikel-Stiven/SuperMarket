package com.example.SuperMarket.modules.personal.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

import org.springframework.stereotype.Controller;

@Entity
@Table(name = "employees")
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
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private Role role;
}
