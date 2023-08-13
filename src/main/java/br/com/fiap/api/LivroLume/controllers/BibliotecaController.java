package br.com.fiap.api.LivroLume.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BibliotecaController {
    @GetMapping("/biblioteca")
    public String bibliotecaPage() {
        return "biblioteca";
    }
}
