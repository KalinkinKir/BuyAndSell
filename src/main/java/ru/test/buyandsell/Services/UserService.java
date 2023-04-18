package ru.test.buyandsell.Services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.test.buyandsell.Models.Enums.Role;
import ru.test.buyandsell.Models.User;
import ru.test.buyandsell.Repositories.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public boolean createUser(User user){
        String email = user.getEmail();
        if(userRepository.findByEmail(email) != null) return false;
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));           //таким образом шифруем пароль
        user.getRoles().add(Role.ROLE_USER);
        log.info("saving new User with e-mail: {}", email);
        userRepository.save(user);
        return true;

    }

    public List<User> list(){
        return userRepository.findAll();
    }
    public void userBan(Long id){                                                                       //находим юзера по id, проверяем активность и баним/дебаним
        User user = userRepository.findById(id).orElse(null);
        if (user != null){
            if (user.isActive()){
                user.setActive(false);
                log.info("Ban user with id = {}; email = {}", user.getId(), user.getEmail());

            } else {
                user.setActive(true);
                log.info("Unban user with id = {}; email = {}", user.getId(), user.getEmail());
            }

            userRepository.save(user);
        }
    }

    public void changeUserRole(User user, Map<String, String> form) {
        Set<String> roles = Arrays.stream(Role.values())                    //преобразуем роли в строковый вид и в сет
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key : form.keySet()) {
            if (roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepository.save(user);
    }
}
