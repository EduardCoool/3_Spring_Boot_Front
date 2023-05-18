package spring.service;

import spring.model.User;

import java.util.List;

public interface UserService {
    User findUserById(Long userId);

    List<User> allUsers();

    boolean save(User user);

    boolean registrationUser(User user);
    boolean update(User user);

    boolean deleteUser(Long userId);
}
