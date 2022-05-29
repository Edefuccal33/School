package com.backend.school.dto;

import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherDTO {
    
    private Long id;
    @NotBlank
    private String name;
    private String createdDate;
    private String lastModifiedDate;
    private List<SubjectDTO> subjects;
}
