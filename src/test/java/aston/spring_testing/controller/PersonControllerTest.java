package aston.spring_testing.controller;

import aston.spring_testing.SpringTestingApplication;
import aston.spring_testing.dto.PersonDto;
import aston.spring_testing.facade.PersonDataFacade;
import aston.spring_testing.module.Person;
import aston.spring_testing.module.PersonRole;
import aston.spring_testing.module.Role;
import aston.spring_testing.repository.PersonRepository;
import aston.spring_testing.repository.PersonRoleRepository;
import aston.spring_testing.repository.RoleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles("test")
@SpringBootTest(classes = SpringTestingApplication.class)
@AutoConfigureMockMvc
@Testcontainers
class PersonControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PersonDataFacade personDataFacade;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonRoleRepository personRoleRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    void savePersonTest() throws Exception {
        PersonDto personDto = new PersonDto("Tom", Set.of("user", "admin"));
        this.mockMvc.perform(
                post("/person/save")
                        .content(objectMapper.writeValueAsString(personDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("name").value("Tom"));

    }

    @Test
    void findPersonTest() throws Exception {
        Person person = new Person("Tom");
        insertPersonToDb(person);

        this.mockMvc.perform(
                get("/person/find/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("name").value("Tom"));

    }


    @Transactional
    private void insertPersonToDb(Person person){
        Person savedPerson = personRepository.save(person);
        Role userRole = roleRepository.save(new Role("user"));
        Role adminRole = roleRepository.save(new Role("admin"));
        PersonRole personRole = new PersonRole(savedPerson, userRole);
        PersonRole personRole1 = new PersonRole(savedPerson, adminRole);
        savedPerson.addPersonRole(personRole);
        savedPerson.addPersonRole(personRole1);
    }
}