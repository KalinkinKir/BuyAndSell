package ru.test.buyandsell.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.test.buyandsell.Models.User;
import ru.test.buyandsell.Services.UserService;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    //страница авторизации
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    //страница регистрации
    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    //регистрация нового юзера
    @PostMapping("/registration")
    public String createUser(User user, Model model){
        if(!userService.createUser(user)){
            model.addAttribute("errorMessage", "Пользователь с таким e-mail: " + user.getEmail() + " уже существует!");
            return "registration";
        }
        return "redirect:/login";
    }

    //страница с подробной информацией о конкретном юзере
    @GetMapping("/user/{user}")
    public String userInfo(@PathVariable("user") User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("products", user.getProducts());
        return "user-info";

    }

}
