package com.backend.school.repository;

import java.util.List;
import com.backend.school.entity.Student;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StudentRepository extends JpaRepository<Student,Long>, JpaSpecificationExecutor<Student>{
    
    List<Student> findAll(Specification<Student> spec);
}
