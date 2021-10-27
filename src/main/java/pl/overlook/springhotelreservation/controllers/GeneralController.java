package pl.overlook.springhotelreservation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.overlook.springhotelreservation.user.User;

import javax.validation.Valid;

@Controller
public class GeneralController {

    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @RequestMapping("/staffInterface")
    public String staffInterface() {
        return "staffInterface";
    }

    @GetMapping("/loginform")
    public String loginForm(Model model){
        model.addAttribute("user", new User());

        return "login_form";
    }

    @PostMapping("/loginform")
    public String addUser(@ModelAttribute @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "login_form";
        else {
            return "index";
        }
    }
}
