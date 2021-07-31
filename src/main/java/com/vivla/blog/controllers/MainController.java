package com.vivla.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    public MainController() {
    }

    @GetMapping({"/"})
    public String home(Model model) {
        model.addAttribute("title", "Главная страница");
        return "home";
    }

    @GetMapping({"/aboutUs"})
    public String about(Model model) {
        model.addAttribute("title", "О нас");
        return "aboutUs";
    }
}
