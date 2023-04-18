package ru.test.buyandsell.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.test.buyandsell.Models.Enums.Role;
import ru.test.buyandsell.Models.User;
import ru.test.buyandsell.Services.UserService;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    private final UserService userService;
    @GetMapping("/admin")
    public String admin(Model model){
        model.addAttribute("users", userService.list());
        return "admin";
    }
    @PostMapping("/admin/user/ban/{id}")
    public String userBan(@PathVariable ("id") Long id){
        userService.userBan(id);
        return "redirect:/admin";
    }
    @GetMapping("/admin/user/edit/{user}")
    public String userEdit(@PathVariable("user") User user, Model model){
        model.addAttribute("users", user);
        model.addAttribute("roles", Role.values());
        return "user-edit";
    }
    @PostMapping("/admin/user/edit")
    public String userEdit(@RequestParam("userId") User user, @RequestParam Map<String,String> form){
        userService.changeUserRole(user, form);
        return "redirect:/admin";
    }
}