package spring.service;

import spring.model.User;
import spring.model.dto.UserDTO;
import spring.model.dto.UserToViewDTO;

import java.util.List;

public interface UserService {
    UserToViewDTO getUserByUsername(String username);

    List<UserToViewDTO> allUsers();

    boolean save(User user);

    boolean addNewUser(UserDTO userDTO);

    boolean update(UserDTO user);

    boolean deleteUser(Long userId);
}
