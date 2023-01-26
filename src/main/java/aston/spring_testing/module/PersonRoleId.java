package aston.spring_testing.module;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class PersonRoleId implements Serializable {
    private Long personId;
    private Long roleId;
}
