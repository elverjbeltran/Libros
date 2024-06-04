package com.example.app.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/Solicitar-libro")
    public String requestBook() {
        return "Solicitar-libro"; // Asegúrate de tener un archivo request-book.html en templates
    }
}

