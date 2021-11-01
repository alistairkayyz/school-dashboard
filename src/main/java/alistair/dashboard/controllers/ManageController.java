package alistair.dashboard.controllers;

import alistair.dashboard.models.User;
import alistair.dashboard.utilities.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManageController {

    @GetMapping("manage-users")
    public String manageUsers(Model model) {
        if (Session.getSessionUser() == null) {
            model.addAttribute("user", new User());
            return "login";
        }
        return "manage-users";
    }

    @GetMapping("manage-staff")
    public String manageStaff(Model model) {
        if (Session.getSessionUser() == null) {
            model.addAttribute("user", new User());
            return "login";
        }
        return "manage-staff";
    }
}
