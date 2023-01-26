package aston.spring_testing.facade;

import aston.spring_testing.dto.PersonDto;
import aston.spring_testing.module.Person;
import aston.spring_testing.module.PersonRole;
import aston.spring_testing.module.Role;
import aston.spring_testing.service.PersonService;
import aston.spring_testing.service.RoleService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
class PersonDataFacadeTest {
    @InjectMocks
    private PersonDataFacade personDataFacade;

    @Mock
    private PersonService personService;

    @Mock
    private RoleService roleService;


    @Test
    void savePersonTest() {
        PersonDto personDto = new PersonDto("Tom", Set.of("user", "admin"));
        Person person = new Person(personDto.getName());
        Person savedPerson = new Person("Tom");
        savedPerson.setId(1L);

        when(personService.save(person)).thenReturn(savedPerson);
        //verify(personDataFacade, times(2)).getSavedRole(anyString());
        PersonDto result = personDataFacade.save(personDto);
        assertEquals(result.getName(), personDto.getName());

    }

    @Test
    void findPersonTest() {
        String personName = "Tom";
        long personId = 1L;
        Person person = new Person(personName);
        person.setId(personId);
        PersonRole personRole = new PersonRole(person, new Role("user"));
        PersonRole personRole1 = new PersonRole(person, new Role("admin"));
        person.addPersonRole(personRole);
        person.addPersonRole(personRole1);


        when(personService.find(personId)).thenReturn(person);
        PersonDto result = personDataFacade.find(personId);
        assertThat(result.getName()).isEqualTo(personName);
        assertThat(result.getRoles().size()).isEqualTo(2);

    }

    @Test
    void findAllPerson() {

    }

    @Test
    void delete() {
    }

    @Test
    void getSavedRole() {
    }
}