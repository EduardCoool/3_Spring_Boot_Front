package spring.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.dao.RoleRepository;
import spring.model.Role;
import spring.model.User;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class MapperDTO {
    @Autowired
    private RoleRepository roleRepository;

    public UserDTO toDto(User user) {
        List<String> roles = user
                .getRoles()
                .stream()
                .map(Role::getRole).toList();


        return new UserDTO(user.getId(), user.getUsername(), user.getLastName(), user.getAge(), user.getEmail(),
                user.getPassword(), roles.contains("ROLE_USER") ? "ROLE_USER" : "",
                roles.contains("ROLE_ADMIN") ? "ROLE_ADMIN" : "");
    }

    public User toUser(UserDTO userDTO) {
        Set<Role> roles = new HashSet<>();
        if (!userDTO.getRoleAdmin().equals("")) {
            roles.add(roleRepository.getRoleByRole("ROLE_USER"));
        }
        if(!userDTO.getRoleUser().equals("")){
            roles.add(roleRepository.getRoleByRole("ROLE_ADMIN"));
        }
        return new User(userDTO.getId(), userDTO.getUsername(), userDTO.getLastName(), userDTO.getPasswordDto(),
                userDTO.getAge(), userDTO.getEmail(), roles);
    }
}
