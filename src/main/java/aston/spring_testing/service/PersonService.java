package aston.spring_testing.service;

import aston.spring_testing.module.Person;
import aston.spring_testing.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PersonService {
    private final PersonRepository personRepository;

    public Person save(Person person){
        return personRepository.save(person);
    }

    public Person find(Long id){
        return personRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public List<Person> findAll(){
        return personRepository.findAll();
    }

    public void delete(Long id){
        personRepository.deleteById(id);
    }

}
