package aston.spring_testing.controller;

import aston.spring_testing.dto.RoleDto;
import aston.spring_testing.facade.RoleDataFacade;
import aston.spring_testing.module.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController {

    private final RoleDataFacade roleDataFacade;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Role save(@RequestBody Role role){
        return roleDataFacade.save(role);
    }

    @GetMapping("/find/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public RoleDto find(@PathVariable Long id){
        return roleDataFacade.find(id);
    }

    @GetMapping("/find_by_role/{role}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public RoleDto findByRole(@PathVariable String role){
        return roleDataFacade.findByRole(role);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable Long id){
        roleDataFacade.delete(id);
    }
}
