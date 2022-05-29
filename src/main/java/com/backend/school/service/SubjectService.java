package com.backend.school.service;

import java.util.List;
import com.backend.school.dto.SubjectDTO;

public interface SubjectService {
    
    List<SubjectDTO> getAll();

    SubjectDTO save(SubjectDTO dto);

    SubjectDTO update(Long id, SubjectDTO dto) throws Exception;

    void delete(Long id);

    SubjectDTO enrollStudent(Long subjectId, Long studentId) throws Exception;

    void unenrollStudent(Long subjectId, Long studentId) throws Exception;

    SubjectDTO assignTeacher(Long subjectId, Long teacherId) throws Exception;

    void unassignTeacher(Long subjectId) throws Exception;
}
