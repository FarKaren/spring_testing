package aston.spring_testing.facade;

import aston.spring_testing.dto.RoleDto;
import aston.spring_testing.module.PersonRole;
import aston.spring_testing.module.Role;
import aston.spring_testing.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RoleDataFacade {
    private final RoleService roleService;

    public Role save(Role role){
        return roleService.save(role);
    }

    public RoleDto find(Long id){
        Role role = roleService.find(id);
        Set<String> personsName = getPersonsName(role);
        return new RoleDto(role.getRole(), personsName);

    }

    public RoleDto findByRole(String role){
        Role roleFromDb = roleService.findByRole(role);
        Set<String> personsName = getPersonsName(roleFromDb);
        return new RoleDto(role, personsName);
    }

    public void delete(Long id){
        roleService.delete(id);
    }

    private Set<String> getPersonsName(Role role){
        return role.getPersonRoles()
                .stream()
                .map(personRole -> personRole.getPerson().getName())
                .collect(Collectors.toSet());
    }
}
