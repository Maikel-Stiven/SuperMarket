package com.example.SuperMarket.modules.personal.repositories;

import com.example.SuperMarket.modules.personal.entities.Employee;
import com.example.SuperMarket.modules.personal.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

public class EmployeeRepository {

    @Repository
    public interface EmployeeRepository extends JpaRepository<Employee, String> {
       
        List<Employee> findByRol(Role rol);
        
    }
    
}
