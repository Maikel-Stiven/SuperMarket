package com.example.repaso.modules.personal.repositories;

import com.example.repaso.modules.personal.entities.Employee;
import com.example.repaso.modules.personal.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
    List<Employee> findByRol(Role rol);

    List<Employee> findByfechaIngresoBetween(LocalDate startDate, LocalDate endDate);
}
