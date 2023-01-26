package aston.spring_testing.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class RoleDto {
    private String role;
    private Set<String> personsName;

}
