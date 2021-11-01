package alistair.dashboard.controllers;

import alistair.dashboard.models.User;
import alistair.dashboard.utilities.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {

    @GetMapping("login")
    public String login(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("validate", false);
        return "login";
    }

    @PostMapping("login")
    public String changePass(@ModelAttribute User user, Model model){
        System.out.println(user.toString());
        if (!user.getRole().equals("Admin")) {
            model.addAttribute("validate", true);

            return "login";
        }
        else if (!user.getPassword().equals("me")) {
            model.addAttribute("validate", true);

            return "login";
        }
        else if (user.getId() != 1) {
            model.addAttribute("validate", true);

            return "login";
        }
        else {
            Session.setSessionUser(user);
            return "redirect:/dashboard";
        }
    }


    @GetMapping("reset-password")
    public String resetPassword(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("validate", false);
        model.addAttribute("confirmPass", false);

        return "reset-password";
    }
}
