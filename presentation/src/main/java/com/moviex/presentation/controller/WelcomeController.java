package com.moviex.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {

    @RequestMapping({"/", "/home"})
    public String showWelcomePage() {
        return "static/views/index.html";
    }
}
