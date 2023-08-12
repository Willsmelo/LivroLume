package br.com.fiap.api.LivroLume.controllers;

import br.com.fiap.api.LivroLume.dto.UpdateLivroDTO;
import br.com.fiap.api.LivroLume.entities.Livro;
import br.com.fiap.api.LivroLume.enums.Genero;
import br.com.fiap.api.LivroLume.services.LivroServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/livros")
public class LivroController {

    @Autowired
    private LivroServices livroServices;
    @GetMapping
    public ResponseEntity<List<Livro>> findAll() {
        List<Livro> livros = livroServices.findAll();
        return ResponseEntity.ok(livros);
    }



    @GetMapping({"/classificacao/greater/{i}"})
    public ResponseEntity<List<Livro>> findByClassificacaoGreater(@PathVariable int i) {
        List<Livro> livros = livroServices.findByClassificacaoIndicativaisGreaterThanEqual(i);
        return ResponseEntity.ok(livros);
    }

    @GetMapping("/classificacao/less/{i}")
    public ResponseEntity<List<Livro>> findByClassificacaoLess(@PathVariable int i) {
        List<Livro> livros = livroServices.findByClassificacaoIndicativaisLessThanEqual(i);
        return ResponseEntity.ok(livros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> findById(@PathVariable long id) {
        Livro livro = livroServices.findById(id);
        return ResponseEntity.ok(livro);
    }

    @GetMapping("/nome/{name}")
    public ResponseEntity<Livro> findByName(@PathVariable String name) {
        Livro livro = livroServices.findByName(name);
        return ResponseEntity.ok(livro);
    }

    @GetMapping("/genero/{genero}")
    public ResponseEntity<List<Livro>> findByGenero(@PathVariable Genero genero) {
        List<Livro> livros = livroServices.findListByGenero(genero);
        return ResponseEntity.ok(livros);
    }

    @PostMapping
    public ResponseEntity<Livro> registerLivro(@RequestBody Livro livro, UriComponentsBuilder uriBuilder) {
        Livro livrosSalvo = livroServices.register(livro);
        if (livrosSalvo != null) {
            URI uri = uriBuilder.path("/ClientePF/{id}").buildAndExpand(livrosSalvo.getId()).toUri();
            return ResponseEntity.created(uri).body(livrosSalvo);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/update")
    public ResponseEntity<Livro> updateLivro(@RequestBody UpdateLivroDTO data) {
        Livro updatedLivro = livroServices.update(data);
        return ResponseEntity.ok(updatedLivro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        livroServices.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
