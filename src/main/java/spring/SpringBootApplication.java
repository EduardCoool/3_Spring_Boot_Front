package spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import spring.dao.RoleRepository;
import spring.model.Role;
import spring.model.User;
import spring.service.UserDetailsServiceImpl;

import java.util.Set;


@org.springframework.boot.autoconfigure.SpringBootApplication
public class SpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(UserDetailsServiceImpl repository, RoleRepository roleRepository) {
        return (args) -> {
            Role roleUser = new Role(1L, "ROLE_USER");
            Role roleAdmin = new Role(2L, "ROLE_ADMIN");


            User user = new User(1L, "ivan", "ivanovich","user",30,
                    "ivan@mail.ru", Set.of(roleUser));
            User admin = new User(2L, "eduard", "proskurin","admin",25,
                    "eduard@mail.ru", Set.of(roleAdmin));
            User adminAndUser =
                    new User(3L, "adminAndUser", "polymorph","adminAndUser",45,
                            "polymorph@mail.ru",Set.of(roleAdmin, roleUser));

            repository.save(user);
            repository.save(admin);
            repository.save(adminAndUser);
        };
    }
}
