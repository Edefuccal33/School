package com.backend.school.dto;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentFiltersDTO {
    
    private String name;
    private String birthDate;
    private Set<Long> subjects;
    private String order;

    public StudentFiltersDTO(String name, String birthDate, Set<Long> subjects, String order) {
        this.name = name;
        this.birthDate = birthDate;
        this.subjects = subjects;
        this.order = order;
    }

    public boolean isASC(){return this.order.compareToIgnoreCase("ASC") == 0;}

    public boolean isDESC(){return this.order.compareToIgnoreCase("DESC") == 0;}
}
