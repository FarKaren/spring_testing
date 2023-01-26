package aston.spring_testing.repository;

import aston.spring_testing.module.PersonRole;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;

@Registered
public interface PersonRoleRepository extends JpaRepository<PersonRole, Long> {
}
