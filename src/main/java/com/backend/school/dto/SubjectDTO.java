package com.backend.school.dto;

import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectDTO {
    
    private Long id;
    @NotBlank
    private String name;
    private String createdDate;
    private String lastModifiedDate;
    private List<StudentDTO> enrolledStudents;
    private TeacherDTO teacher;
}
