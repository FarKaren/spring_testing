package aston.spring_testing.module;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "person_role")
public class PersonRole {
    @EmbeddedId
    private PersonRoleId personRoleId = new PersonRoleId();

    @MapsId("personId")
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person = new Person();

    @MapsId("roleId")
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role = new Role();

    public PersonRole(Person person, Role role) {
        this.person = person;
        this.role = role;
    }

}
