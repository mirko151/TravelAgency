package com.travelagency.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        // Redirektuje osnovni URL na stranicu koja prikazuje sva putovanja
        return "redirect:/travel/all";
    }
}
