package com.backend.school.controller;

import java.util.List;
import java.util.Set;
import javax.validation.Valid;
import com.backend.school.dto.StudentDTO;
import com.backend.school.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;

@RestController
@CrossOrigin(origins = "*")
@AllArgsConstructor
@RequestMapping("students")
public class StudentController {
    
    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAll(){
        return ResponseEntity.ok().body(studentService.getAll());
    }

    @GetMapping("/custom")
    public ResponseEntity<List<StudentDTO>> getByFilters(
        @RequestParam(required=false) String name,
        @RequestParam(required =false) String birthDate,
        @RequestParam(required =false) Set<Long> subjects,
        @RequestParam(required =false, defaultValue ="ASC") String order
    ){
        return ResponseEntity.ok().body(studentService.getByFilters(name, birthDate, subjects, order));
    }

    @PostMapping
    public ResponseEntity<StudentDTO> create(@Valid @RequestBody StudentDTO student){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.save(student));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> update(@PathVariable Long id, @Valid @RequestBody StudentDTO student) throws Exception{
        return ResponseEntity.ok().body(studentService.update(id, student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
}
