package com.example.repaso.modules.personal.repositories;

import com.example.repaso.modules.personal.entities.Employee;
import com.example.repaso.modules.personal.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    
}
