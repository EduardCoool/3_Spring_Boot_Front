package spring.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.dao.RoleRepository;
import spring.model.Role;
import spring.model.User;
import spring.model.UserDTO;
import spring.model.UserToViewDTO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class MapperUserDTO {
    private final String ROLE_USER = "ROLE_USER";

    private final String ROLE_ADMIN = "ROLE_ADMIN";

    private final String ROLE_USER_TO_VIEW = "USER";

    private final String ROLE_ADMIN_TO_VIEW = "ADMIN";

    @Autowired
    private RoleRepository roleRepository;

    public UserToViewDTO toDto(User user) {
        List<String> roles = user
                .getRoles()
                .stream()
                .map(Role::getRole).toList();

        return new UserToViewDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getAge(), user.getEmail(),
                user.getPassword(), roles.contains(ROLE_USER) ? ROLE_USER_TO_VIEW : "",
                roles.contains(ROLE_ADMIN) ? ROLE_ADMIN_TO_VIEW : "");
    }

    public User toUser(UserDTO userDTO) {
        Set<Role> roles = new HashSet<>();
        Set<String> rolesDTO = userDTO.getRoles();

        if (rolesDTO.contains(ROLE_USER_TO_VIEW)) {
            roles.add(roleRepository.getRoleByRole(ROLE_USER));
        }
        if (rolesDTO.contains(ROLE_ADMIN_TO_VIEW)) {
            roles.add(roleRepository.getRoleByRole(ROLE_ADMIN));
        }
        return new User(userDTO.getId(), userDTO.getUsername(), userDTO.getLastName(), userDTO.getPasswordDto(),
                userDTO.getAge(), userDTO.getEmail(), roles);
    }
}
