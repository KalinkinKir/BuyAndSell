package ru.test.buyandsell.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.test.buyandsell.Models.Enums.Role;
import ru.test.buyandsell.Models.User;
import ru.test.buyandsell.Services.UserService;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@RequestMapping("/admins")
@Tag(name = "Контроллер админов", description = "Отвечает за панель администратора, блокировку и смену ролей пользователей")
public class AdminController {
    private final UserService userService;

    @Operation(summary = "Страница с панелью админа")
    @GetMapping("/adminPanel")
    public String admin(Model model){
        model.addAttribute("users", userService.list());
        return "admin";
    }

    @Operation(summary = "Бан юзера")
    @PostMapping("/userBan/{id}")
    public String userBan(@PathVariable("id") Long id){
        userService.userBan(id);
        return "redirect:/admins/adminPanel";
    }

    @Operation(summary = "Страница, где можно поменять роль юзера")
    @GetMapping("/userEdit/{user}")
    public String userEdit(@PathVariable("user") User user, Model model){
        model.addAttribute("users", user);
        model.addAttribute("roles", Role.values());
        return "user-edit";
    }

    @Operation(summary = "Изменение роли юзера")
    @PostMapping("/userEdit")
    public String userEdit(@RequestParam("userId") User user, @RequestParam Map<String,String> form){
        userService.changeUserRole(user, form);
        return "redirect:/admins/adminPanel";
    }
}
