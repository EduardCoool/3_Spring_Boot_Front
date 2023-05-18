package spring.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import spring.dao.RoleRepository;
import spring.model.User;
import spring.service.UserService;

import java.util.List;


@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;




    @GetMapping(value = "/service")
    public List<User> viewUsers() {
        return userService.allUsers();
    }

    @GetMapping(value = "/{id}")
    public User findById(@PathVariable("id") Long id) {
        return userService.findUserById(id);
    }

    @PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean addUser(@RequestBody User user) {
        return userService.registrationUser(user);
    }

    @PutMapping(value = "/update")
    @ResponseStatus(HttpStatus.OK)
    public boolean update(@RequestBody User user) {
        return userService.update(user);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

}
