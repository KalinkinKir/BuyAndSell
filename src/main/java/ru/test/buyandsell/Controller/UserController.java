package ru.test.buyandsell.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.test.buyandsell.Models.User;
import ru.test.buyandsell.Services.ProductService;
import ru.test.buyandsell.Services.UserService;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@Tag(name = "Контроллер юзера", description = "Отвечает за главную страницу, а также страницы регистрации, авторизации и информации о конкретном пользователе")
public class UserController {
    private final UserService userService;
    private final ProductService productService;

    @Operation(summary = "Главная страница")
    @GetMapping("/")
    public String products(@RequestParam(name = "title", required = false) String title, Principal principal, Model model){
        model.addAttribute("products", productService.listProducts(title));
        model.addAttribute("user", productService.getUserByPrincipal(principal));
        return "products";
    }

    @Operation(summary = "Страница авторизации")
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @Operation(summary = "Страница регистрации")
    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @Operation(summary = "Регистрация нового юзера")
    @PostMapping("/registration")
    public String createUser(User user, Model model){
        if(!userService.createUser(user)){
            model.addAttribute("errorMessage", "Пользователь с таким e-mail: " + user.getEmail() + " уже существует!");
            return "registration";
        }
        return "redirect:/login";
    }

    @Operation(summary = "Страница с подробной информацией о конкретном юзере")
    @GetMapping("/userInfo/{user}")
    public String userInfo(@PathVariable("user") User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("products", user.getProducts());
        return "user-info";
    }

}
