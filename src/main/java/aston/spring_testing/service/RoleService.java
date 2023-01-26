package aston.spring_testing.service;

import aston.spring_testing.module.Role;
import aston.spring_testing.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public Role find(Long id){
        return roleRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public Role save(Role role){
        return roleRepository.save(role);
    }

    public Role findByRole(String role){
        return roleRepository.findByRole(role).orElseThrow(RuntimeException::new);
    }

    public Role findByRoleOrElseNull(String role){
        return roleRepository.findByRole(role).orElse(null);
    }

    public void delete(Long id){
        roleRepository.deleteById(id);
    }
}
