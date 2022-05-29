package com.backend.school.controller;

import java.util.List;
import javax.validation.Valid;
import com.backend.school.dto.SubjectDTO;
import com.backend.school.service.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("subjects")
public class SubjectController {
    
    private final SubjectService subjectService;

    @GetMapping
    public ResponseEntity<List<SubjectDTO>> getAll(){
        return ResponseEntity.ok().body(subjectService.getAll());
    }

    @PostMapping
    public ResponseEntity<SubjectDTO> create(@Valid @RequestBody SubjectDTO subject){
        return ResponseEntity.status(HttpStatus.CREATED).body(subjectService.save(subject));
    }

    @PutMapping("/{subjectId}/students/{studentId}")
    public ResponseEntity<SubjectDTO> enrollStudentToSubject(
                @PathVariable Long subjectId,
                @PathVariable Long studentId
    )throws Exception{  
        return ResponseEntity.ok().body(subjectService.enrollStudent(subjectId, studentId));
    }

    @PutMapping("/{subjectId}/teachers/{teacherId}")
    public ResponseEntity<SubjectDTO> assignTeacherToSubject(
                @PathVariable Long subjectId,
                @PathVariable Long teacherId
    )throws Exception{  
        return ResponseEntity.ok().body(subjectService.assignTeacher(subjectId, teacherId));
    }

    @PatchMapping("/{subjectId}")
    public ResponseEntity<Void> unassignTeacherFromSubject(@PathVariable Long subjectId)throws Exception{
        subjectService.unassignTeacher(subjectId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{subjectId}/students/{studentId}")
    public ResponseEntity<Void> unenrollStudentFromSubject(
                @PathVariable Long subjectId,
                @PathVariable Long studentId
    )throws Exception{
        subjectService.unenrollStudent(subjectId, studentId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        subjectService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubjectDTO> update(@PathVariable Long id, @Valid @RequestBody SubjectDTO subject) throws Exception{
        return ResponseEntity.ok().body(subjectService.update(id, subject));
    }


}
