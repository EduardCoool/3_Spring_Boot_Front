package spring.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.model.User;
import spring.service.UserDetailsServiceImpl;


//@Controller
//@RequestMapping("")
//public class UserContoller {
//
//    @Autowired
//    private UserDetailsServiceImpl userDetailsService;

//    @GetMapping("/user")
//    public String getHome() {
//        return "user";
//    }
//
//    @GetMapping("/registration")
//    public String addNewUser(Model model) {
//        User user = new User();
//        model.addAttribute("newUser", user);
//        return "registration";
//    }
//
//    @PostMapping("/save")
//    public String saveEmployee(@ModelAttribute("newUser") User user) {
//        userDetailsService.registrationUser(user);
//        return "redirect:/signin";
//    }
//}

