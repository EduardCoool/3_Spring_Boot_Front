package spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import spring.dao.RoleRepository;
import spring.dao.UserRepository;
import spring.mapper.MapperUserDTO;
import spring.model.User;
import spring.model.UserDTO;
import spring.model.UserToViewDTO;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService, UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private MapperUserDTO mapperUserDTO;

    @Value("${sortByValue}")
    private String sortByValue;

    public UserDetailsServiceImpl() {
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    @Override
    public UserToViewDTO getUserByUsername(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return mapperUserDTO.toDto(userRepository.findByEmail(email));
    }

    @Override
    public List<UserToViewDTO> allUsers() {
        return userRepository.findAll(Sort.by(sortByValue))
                .stream()
                .map(mapperUserDTO::toDto)
                .toList();
    }

    @Override
    public boolean save(User user) {
        User userFromDB = userRepository.findByEmail(user.getFirstName());
        if (userFromDB != null) {
            return false;
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;

    }

    public boolean addNewUser(UserDTO userDto) {
        User user = mapperUserDTO.toUser(userDto);
        User userFromDB = userRepository.findByEmail(user.getFirstName());
        if (userFromDB != null) {
            return false;
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean update(UserDTO userDto) {
        User user = mapperUserDTO.toUser(userDto);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isEmpty() |
                roleRepository.getRoleByRole("ROLE_ADMIN").getUsers().size() < 2) {
            return false;
        }
        userRepository.deleteById(userId);
        return true;
    }
}


