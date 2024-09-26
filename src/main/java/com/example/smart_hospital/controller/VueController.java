package com.example.smart_hospital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VueController {

    @RequestMapping("/api/home")
    public String redirect() {
        return "index"; // or the name of your HTML file
    }

    // Other mappings...
}
