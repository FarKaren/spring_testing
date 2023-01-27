package aston.spring_testing.repository;

import aston.spring_testing.module.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("test")
@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void savePersonTest(){
        Person person = new Person("name");
        Person savedPerson = personRepository.save(person);
        assertThat(savedPerson).usingRecursiveComparison().ignoringFields("id").isEqualTo(person);
    }

    @Test
    @Sql("classpath:test-data.sql")
    public void find(){
        Person person = personRepository.findById(1L).orElse(null);
        assertThat(person).isNotNull();
    }

}