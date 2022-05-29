package com.backend.school.service.impl;

import java.util.List;
import java.util.Set;

import com.backend.school.dto.StudentDTO;
import com.backend.school.dto.StudentFiltersDTO;
import com.backend.school.entity.Student;
import com.backend.school.exception.ParamNotFound;
import com.backend.school.mapper.StudentMapper;
import com.backend.school.repository.StudentRepository;
import com.backend.school.repository.specification.StudentSpecification;
import com.backend.school.service.StudentService;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepo;
    private final StudentMapper studentMapper;
    private final StudentSpecification studentSpecification;

    @Override
    public List<StudentDTO> getAll() {
        return studentMapper.toDtos(studentRepo.findAll(), true);
    }

    @Override
    public StudentDTO save(StudentDTO dto) {
        Student entity = studentMapper.toEntity(dto);
        Student entitySaved = studentRepo.save(entity);
        return studentMapper.toDto(entitySaved, true);
    }

    @Override
    public StudentDTO update(Long id, StudentDTO dto) throws Exception {
        Student student = studentRepo.findById(id)
                                    .orElseThrow(() -> new ParamNotFound("Student ID not valid."));
        studentMapper.refreshValues(student, dto);
        Student entitySaved = studentRepo.save(student);
        return studentMapper.toDto(entitySaved, true);
    }

    @Override
    public void delete(Long id) {
        studentRepo.deleteById(id);        
    }

    @Override
    public List<StudentDTO> getByFilters(String name, String birthDate, Set<Long> subjects, String order) {
        StudentFiltersDTO filtersDTO = new StudentFiltersDTO(name, birthDate, subjects, order);
        List<Student> entities = studentRepo.findAll(studentSpecification.getByFilters(filtersDTO));
        List<StudentDTO> dtos = studentMapper.toDtos(entities, true);
        return dtos;
    }
    
}
