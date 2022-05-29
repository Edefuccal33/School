package com.backend.school.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.backend.school.dto.StudentDTO;
import com.backend.school.dto.SubjectDTO;
import com.backend.school.entity.Student;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
    
    @Autowired
    private SubjectMapper subjectMapper;

    public Student toEntity(StudentDTO dto){
        Student entity = new Student();
        BeanUtils.copyProperties(dto, entity,"id","createdDate","lastModifiedDate");
        entity.setBirthDate(string2localDate(dto.getBirthDate()));
        return entity;
    }

    public StudentDTO toDto(Student entity, boolean loadSubjects){
        StudentDTO dto = new StudentDTO();
        BeanUtils.copyProperties(entity, dto);
        dto.setBirthDate(entity.getBirthDate().toString());
        dto.setCreatedDate(entity.getCreatedDate().toString());
        dto.setLastModifiedDate(entity.getLastModifiedDate().toString());
        if (loadSubjects){
            List<SubjectDTO> subjectsDTO = subjectMapper.toDtos(entity.getSubjects(), false, false);
            dto.setSubjects(subjectsDTO);
        }
        return dto;
    }

    public List<StudentDTO> toDtos(Collection<Student> entities, boolean loadSubjects){
        List<StudentDTO> dtos = new ArrayList<>();
        for (Student entity : entities) {
            dtos.add(toDto(entity, loadSubjects));
        }
        return dtos;
    }

    public Set<Student> toEntities(Set<StudentDTO> dtos){
        Set<Student> entities = new HashSet<>();
        for (StudentDTO dto : dtos) {
            entities.add(toEntity(dto));
        }
        return entities;
    }

    public void refreshValues(Student target, StudentDTO source){
        BeanUtils.copyProperties(source, target,"id","createdDate","lastModifiedDate");
    }

    private LocalDate string2localDate(String stringDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(stringDate, formatter);
        return date;
    }

}
