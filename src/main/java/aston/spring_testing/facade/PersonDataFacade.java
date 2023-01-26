package aston.spring_testing.facade;

import aston.spring_testing.dto.PersonDto;
import aston.spring_testing.module.Person;
import aston.spring_testing.module.PersonRole;
import aston.spring_testing.module.Role;
import aston.spring_testing.service.PersonService;
import aston.spring_testing.service.RoleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PersonDataFacade {

    private final PersonService personService;
    private final RoleService roleService;

    @Transactional
    public PersonDto save(PersonDto personDto) {
        Person person = new Person(personDto.getName());
        Person savedPerson = personService.save(person);
        Set<PersonRole> personRoles = personDto.getRoles()
                .stream()
                .map(this::getSavedRole)
                .map(role -> new PersonRole(savedPerson, role))
                .collect(Collectors.toSet());

        savedPerson.setPersonRoles(personRoles);
        return personDto;
    }

    public PersonDto find(Long id){
        Person person = personService.find(id);
        Set<String> roles = person.getPersonRoles().stream()
                .map(personRole -> personRole.getRole().getRole())
                .collect(Collectors.toSet());

        return new PersonDto(person.getName(), roles);

    }

    public List<PersonDto> findAll(){
        List<Person> personList = personService.findAll();
        return personList.stream()
                .map(this::getPersonDto)
                .toList();
    }

    public void delete(Long id){
        personService.delete(id);
    }

    private PersonDto getPersonDto(Person person) {
        Set<String> roles = person.getPersonRoles()
                .stream()
                .map(PersonRole::getRole)
                .map(Role::getRole)
                .collect(Collectors.toSet());

        return new PersonDto(person.getName(), roles);
    }

    @Transactional
    public Role getSavedRole(String role) {
        Role savedRole = roleService.findByRoleOrElseNull(role);
        if(savedRole == null)
            return roleService.save(new Role(role));
        else return savedRole;
    }

}
