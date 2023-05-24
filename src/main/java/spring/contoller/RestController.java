package spring.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import spring.model.UserDTO;
import spring.model.UserToViewDTO;
import spring.service.UserService;

import java.util.List;


@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/authentication")
    public UserToViewDTO getAuthenticationUser(Authentication authentication) {
        return userService.getUserByUsername(authentication.getName());
    }

    @GetMapping(value = "/admin/service")
    public List<UserToViewDTO> viewUsers() {
        return userService.allUsers();
    }


    @PostMapping(value = "/admin/save")
    public boolean addUser(@RequestBody UserDTO userDTO) {
        return userService.addNewUser(userDTO);
    }

    @PutMapping(value = "/admin/update")
    public boolean update(@RequestBody UserDTO user) {
        return userService.update(user);
    }

    @DeleteMapping(value = "/admin/delete/{id}")
    public boolean delete(@PathVariable("id") Long id) {
        return userService.deleteUser(id);
    }
}
