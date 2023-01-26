package aston.spring_testing.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class PersonDto {
    private String name;
    private Set<String> roles;


}
