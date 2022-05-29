package com.backend.school.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.backend.school.dto.SubjectDTO;
import com.backend.school.dto.TeacherDTO;
import com.backend.school.entity.Teacher;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeacherMapper {
    
    @Autowired
    private SubjectMapper subjectMapper;

    public Teacher toEntity(TeacherDTO dto){
        Teacher entity = new Teacher();
        BeanUtils.copyProperties(dto, entity,"id","createdDate","lastModifiedDate");
        return entity;
    }

    public TeacherDTO toDto(Teacher entity, boolean loadSubjects){
        TeacherDTO dto = new TeacherDTO();
        BeanUtils.copyProperties(entity, dto);
        dto.setCreatedDate(entity.getCreatedDate().toString());
        dto.setLastModifiedDate(entity.getLastModifiedDate().toString());
        if (loadSubjects){
            List<SubjectDTO> subjects = subjectMapper.toDtos(entity.getSubjects(), true, false);
            dto.setSubjects(subjects);
        }
        return dto;
    }

    public List<TeacherDTO> toDtos(Collection<Teacher> entities, boolean loadSubjects){
        List<TeacherDTO> dtos = new ArrayList<>();
        for (Teacher entity : entities) {
            dtos.add(toDto(entity, loadSubjects));
        }
        return dtos;
    }

    public List<Teacher> toEntities(List<TeacherDTO> dtos){
        List<Teacher> entities = new ArrayList<>();
        for (TeacherDTO dto : dtos) {
            entities.add(toEntity(dto));
        }
        return entities;
    }

    public void refreshValues(Teacher target, TeacherDTO source){
        BeanUtils.copyProperties(source, target,"id","createdDate","lastModifiedDate");
    }

}
