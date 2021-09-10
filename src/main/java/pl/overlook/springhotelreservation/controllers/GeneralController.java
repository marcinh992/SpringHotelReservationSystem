package pl.overlook.springhotelreservation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GeneralController {

    @RequestMapping("/")
    public String home() {
        return "index";
    }


    @RequestMapping("/staffInterface")
    public String staffInterface(){


        return "staffInterface";
    }
}
