package com.example.app.Controller;


import com.example.app.model.*;
import com.example.app.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/libros")
public class LibroController {

    @Autowired
    private LibroRepository libroRepository;

    @GetMapping
    public String listLibros(Model model) {
        List<Libro> libros = libroRepository.findAll();
        model.addAttribute("libros", libros);
        return "lista-libros";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("libro", new Libro());
        return "crear-libro";
    }

    @PostMapping
    public String createLibro(@ModelAttribute Libro libro) {
        libroRepository.save(libro);
        return "redirect:/libros";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Libro libro = libroRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid libro Id:" + id));
        model.addAttribute("libro", libro);
        return "editar-libro";
    }

    @PostMapping("/update/{id}")
    public String updateLibro(@PathVariable Long id, @ModelAttribute Libro libro) {
        libro.setId(id);
        libroRepository.save(libro);
        return "redirect:/lista-libros";
    }

    @GetMapping("/delete/{id}")
    public String deleteLibro(@PathVariable Long id) {
        libroRepository.deleteById(id);
        return "redirect:/libros";
    }
}
