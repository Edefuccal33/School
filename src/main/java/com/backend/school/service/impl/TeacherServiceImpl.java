package com.backend.school.service.impl;

import java.util.List;
import com.backend.school.dto.TeacherDTO;
import com.backend.school.entity.Teacher;
import com.backend.school.exception.ParamNotFound;
import com.backend.school.mapper.TeacherMapper;
import com.backend.school.repository.TeacherRepository;
import com.backend.school.service.TeacherService;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TeacherServiceImpl implements TeacherService{

    private final TeacherMapper teacherMapper;
    private final TeacherRepository teacherRepo;

    @Override
    public List<TeacherDTO> getAll() {
        return teacherMapper.toDtos(teacherRepo.findAll(), true);
    }

    @Override
    public TeacherDTO save(TeacherDTO dto) {
        Teacher entity = teacherMapper.toEntity(dto);
        Teacher entitySaved = teacherRepo.save(entity);
        return teacherMapper.toDto(entitySaved, true);
    }

    @Override
    public TeacherDTO update(Long id, TeacherDTO dto) throws Exception {
        Teacher teacher = teacherRepo.findById(id)
                                    .orElseThrow(() -> new ParamNotFound("Teacher ID not valid."));
        teacherMapper.refreshValues(teacher, dto);
        Teacher entitySaved = teacherRepo.save(teacher);
        return teacherMapper.toDto(entitySaved, true);
    }

    @Override
    public void delete(Long id) {
        teacherRepo.deleteById(id);
    }
    
}
