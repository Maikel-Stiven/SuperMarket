package com.example.repaso.modules.personal.repositories;

import com.example.repaso.modules.personal.entities.Employee;
import com.example.repaso.modules.personal.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
}
