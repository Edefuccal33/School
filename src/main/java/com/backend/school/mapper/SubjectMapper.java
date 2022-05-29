package com.backend.school.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.backend.school.dto.StudentDTO;
import com.backend.school.dto.SubjectDTO;
import com.backend.school.dto.TeacherDTO;
import com.backend.school.entity.Subject;
import com.backend.school.entity.Teacher;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubjectMapper {
    
    @Autowired
    private StudentMapper studentMapper;

    public Subject toEntity(SubjectDTO dto){
        Subject entity = new Subject();
        BeanUtils.copyProperties(dto, entity,"id","createdDate","lastModifiedDate");
        return entity;
    }

    public SubjectDTO toDto(Subject entity, boolean loadStudents, boolean loadTeacher){
        SubjectDTO dto = new SubjectDTO();
        BeanUtils.copyProperties(entity, dto);
        dto.setCreatedDate(entity.getCreatedDate().toString());
        dto.setLastModifiedDate(entity.getLastModifiedDate().toString());
        Teacher teacher = entity.getTeacher();
        if (teacher != null && loadTeacher){
            TeacherMapper teacherMapper = new TeacherMapper();
            TeacherDTO teacherDTO = teacherMapper.toDto(teacher, false);
            dto.setTeacher(teacherDTO);
        }
        if (loadStudents){
            List<StudentDTO> studentsDTO = studentMapper.toDtos(entity.getEnrolledStudents(), false);
            dto.setEnrolledStudents(studentsDTO);
        }
        return dto;
    }

    public List<SubjectDTO> toDtos(Collection<Subject> entities, boolean loadStudents, boolean loadTeacher){
        List<SubjectDTO> dtos = new ArrayList<>();
        for (Subject entity : entities) {
            dtos.add(toDto(entity, loadStudents, loadTeacher));
        }
        return dtos;
    }

    public List<Subject> toEntities(List<SubjectDTO> dtos){
        List<Subject> entities = new ArrayList<>();
        for (SubjectDTO dto : dtos) {
            entities.add(toEntity(dto));
        }
        return entities;
    }

    public void refreshValues(Subject target, SubjectDTO source){
        BeanUtils.copyProperties(source, target,"id","createdDate","lastModifiedDate");
    }
}
