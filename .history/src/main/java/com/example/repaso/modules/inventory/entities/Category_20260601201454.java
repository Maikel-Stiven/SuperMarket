package com.example.repaso.modules.inventory.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Categorias")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long 
    
}
