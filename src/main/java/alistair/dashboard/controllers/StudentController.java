package alistair.dashboard.controllers;

import alistair.dashboard.models.User;
import alistair.dashboard.utilities.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentController {

    @GetMapping("all-students")
    public String getStudents(Model model){
        if (Session.getSessionUser() == null) {
            model.addAttribute("user", new User());
            return "login";
        }
        return "all-students";
    }

    @GetMapping("new-student")
    public String newStudent(Model model){
        if (Session.getSessionUser() == null) {
            model.addAttribute("user", new User());
            return "login";
        }
        return "new-student";
    }

    @GetMapping("edit-student")
    public String editStudent(Model model){
        if (Session.getSessionUser() == null) {
            model.addAttribute("user", new User());
            return "login";
        }
        return "edit-student";
    }
}
