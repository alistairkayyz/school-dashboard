package alistair.dashboard.controllers;

import alistair.dashboard.models.User;
import alistair.dashboard.utilities.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DataTableController {

    @GetMapping("data-table")
    public String dataTable(Model model){
        if (Session.getSessionUser() == null) {
            model.addAttribute("user", new User());
            return "login";
        }
        return "data-table";
    }

    @PostMapping("data-table/message")
    public String messageStudents(Model model){
        if (Session.getSessionUser() == null) {
            model.addAttribute("user", new User());
            return "login";
        }
        return "data-table";
    }
}
