package com.backend.school.dto;

import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDTO {
    
    private Long id;
    @NotBlank
    private String name;
    @Email
    private String email;
    @NotBlank
    private String birthDate;
    @NotBlank
    private String phoneNumber;
    private String createdDate;
    private String lastModifiedDate;
    private List<SubjectDTO> subjects;
}
