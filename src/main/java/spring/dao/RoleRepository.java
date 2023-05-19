package spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role getRoleByRole(String role);
}
