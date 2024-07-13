package com.alura.literalura.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("livros")
public class LivroController {

    @GetMapping
    public void obterLivros() {
        System.out.println("está online!");
    }
}
