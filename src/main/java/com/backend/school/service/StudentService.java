package com.backend.school.service;

import java.util.List;
import java.util.Set;

import com.backend.school.dto.StudentDTO;

public interface StudentService {
    
    List<StudentDTO> getAll();

    List<StudentDTO> getByFilters(String name, String birthDate, Set<Long> subjects, String order);

    StudentDTO save(StudentDTO dto);

    StudentDTO update(Long id, StudentDTO dto) throws Exception;

    void delete(Long id);
}
