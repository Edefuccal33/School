package com.backend.school.service;

import java.util.List;

import com.backend.school.dto.TeacherDTO;

public interface TeacherService {
    
    List<TeacherDTO> getAll();

    TeacherDTO save(TeacherDTO dto);

    TeacherDTO update(Long id, TeacherDTO dto) throws Exception;

    void delete(Long id);
}
