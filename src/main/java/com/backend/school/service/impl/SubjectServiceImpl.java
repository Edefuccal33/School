package com.backend.school.service.impl;

import java.util.List;
import com.backend.school.dto.SubjectDTO;
import com.backend.school.entity.Student;
import com.backend.school.entity.Subject;
import com.backend.school.entity.Teacher;
import com.backend.school.exception.ParamNotFound;
import com.backend.school.mapper.SubjectMapper;
import com.backend.school.repository.StudentRepository;
import com.backend.school.repository.SubjectRepository;
import com.backend.school.repository.TeacherRepository;
import com.backend.school.service.SubjectService;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SubjectServiceImpl implements SubjectService{

    private final SubjectRepository subjectRepo;
    private final StudentRepository studentRepo;
    private final TeacherRepository teacherRepo;
    private final SubjectMapper subjectMapper;

    @Override
    public List<SubjectDTO> getAll() {
        return subjectMapper.toDtos(subjectRepo.findAll(), true, true);
    }

    @Override
    public SubjectDTO save(SubjectDTO dto) {
        Subject entity = subjectMapper.toEntity(dto);
        Subject entitySaved = subjectRepo.save(entity);
        return subjectMapper.toDto(entitySaved, true, true);
    }

    @Override
    public SubjectDTO update(Long id, SubjectDTO dto) throws Exception {
        Subject subject = subjectRepo.findById(id)
                                    .orElseThrow(() -> new ParamNotFound("Subject ID not valid."));
        subjectMapper.refreshValues(subject, dto);
        Subject entitySaved = subjectRepo.save(subject);
        return subjectMapper.toDto(entitySaved, true, true);
    }

    @Override
    public void delete(Long id) {
        subjectRepo.deleteById(id);
    }

    @Override
    public SubjectDTO enrollStudent(Long subjectId, Long studentId) throws Exception {
        Subject subject = subjectRepo.findById(subjectId)
                                    .orElseThrow(() -> new ParamNotFound("Subject ID not valid."));
        Student student = studentRepo.findById(studentId)
                                    .orElseThrow(() -> new ParamNotFound("Student ID not valid."));
        subject.enrollStudent(student);
        return subjectMapper.toDto(subjectRepo.save(subject),true, true);
    }

    @Override
    public void unenrollStudent(Long subjectId, Long studentId) throws Exception {
        Subject subject = subjectRepo.findById(subjectId)
                                    .orElseThrow(() -> new ParamNotFound("Subject ID not valid."));
        Student student = studentRepo.findById(studentId)
                                    .orElseThrow(() -> new ParamNotFound("Student ID not valid."));
        if(subject.getEnrolledStudents().contains(student)){
            subject.unenrollStudent(student);
            subjectRepo.save(subject);
        }else{
            throw new ParamNotFound("Student ID provided does not match with enrolled students for this subject");
        }
    }

    @Override
    public SubjectDTO assignTeacher(Long subjectId, Long teacherId) throws Exception {
        Subject subject = subjectRepo.findById(subjectId)
                                    .orElseThrow(() -> new ParamNotFound("Subject ID not valid."));
        Teacher teacher = teacherRepo.findById(teacherId)
                                    .orElseThrow(() -> new ParamNotFound("Teacher ID not valid."));
        subject.assignTeacher(teacher);
        Subject entitySaved = subjectRepo.save(subject);
        return subjectMapper.toDto(entitySaved,true, true);
    }

    @Override
    public void unassignTeacher(Long subjectId) throws Exception {
        Subject subject = subjectRepo.findById(subjectId)
                                    .orElseThrow(() -> new ParamNotFound("Subject ID not valid."));
        subject.unassignTeacher();
        subjectRepo.save(subject);
    }
    
}
