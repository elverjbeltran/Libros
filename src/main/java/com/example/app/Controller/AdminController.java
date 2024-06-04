package com.example.app.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/index")
    public String admin() {
        return "index"; // Asegúrate de tener un archivo admin.html en templates
    }
}
