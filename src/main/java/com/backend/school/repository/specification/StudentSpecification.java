package com.backend.school.repository.specification;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import com.backend.school.dto.StudentFiltersDTO;
import com.backend.school.entity.Student;
import com.backend.school.entity.Subject;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.util.CollectionUtils;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Expression;

@Component
public class StudentSpecification {
    
    public Specification<Student> getByFilters(StudentFiltersDTO filtersDTO) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(filtersDTO.getName())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")),
                                "%" + filtersDTO.getName().toLowerCase() + "%"
                        )
                );
            }
            
            if (StringUtils.hasLength(filtersDTO.getBirthDate())) {
                // TODO: Reuse this in a function
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(filtersDTO.getBirthDate(), formatter);

                predicates.add(
                        criteriaBuilder.equal(root.<LocalDate>get("birthDate"), date)
                );
            }
            if (!CollectionUtils.isEmpty(filtersDTO.getSubjects())) {
                Join<Subject, Student> join = root.join("subjects", JoinType.INNER);
                Expression<String> subjectsId = join.get("id");
                predicates.add(subjectsId.in(filtersDTO.getSubjects()));
            }
            // Remove duplicates
            query.distinct(true);

            //Order resolver
            String orderByField = "name";
            query.orderBy(
                    filtersDTO.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField))
            );
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
    
}
