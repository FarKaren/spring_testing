package aston.spring_testing.controller;

import aston.spring_testing.dto.PersonDto;
import aston.spring_testing.facade.PersonDataFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/person")
public class PersonController {
    private final PersonDataFacade personDataFacade;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PersonDto save(@RequestBody PersonDto personDto){
        return personDataFacade.save(personDto);
    }

    @GetMapping("/find/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PersonDto find(@PathVariable Long id){
        return personDataFacade.find(id);
    }

    @GetMapping("/find_all")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<PersonDto> findAll(){
        return personDataFacade.findAll();
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable Long id){
        personDataFacade.delete(id);
    }

}
