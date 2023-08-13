package br.com.fiap.api.LivroLume.controllers;

import br.com.fiap.api.LivroLume.dto.UpdateLivroDTO;
import br.com.fiap.api.LivroLume.entities.Livro;
import br.com.fiap.api.LivroLume.enums.Genero;
import br.com.fiap.api.LivroLume.services.LivroServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import io.swagger.annotations.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/livros")
public class LivroController {

    @Autowired
    private LivroServices livroServices;

    @GetMapping
    @ApiOperation(value = "Buscar todos os livros")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Livros encontrados com sucesso"),
            @ApiResponse(code = 500, message = "Erro interno no servidor")
    })
    public ResponseEntity<List<Livro>> findAll() {
        List<Livro> livros = livroServices.findAll();
        return ResponseEntity.ok(livros);
    }



    @GetMapping("/classificacao/greater/{i}")
    @ApiOperation(value = "Buscar livros por classificação maior ou igual")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Livros encontrados com sucesso"),
            @ApiResponse(code = 500, message = "Erro interno no servidor")
    })
    public ResponseEntity<List<Livro>> findByClassificacaoGreater(
            @ApiParam(value = "Classificação indicativa mínima", required = true) @PathVariable int i) {
        List<Livro> livros = livroServices.findByClassificacaoIndicativaisGreaterThanEqual(i);
        return ResponseEntity.ok(livros);
    }

    @GetMapping("/classificacao/less/{i}")
    @ApiOperation(value = "Buscar livros por classificação menor ou igual")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Livros encontrados com sucesso"),
            @ApiResponse(code = 500, message = "Erro interno no servidor")
    })
    public ResponseEntity<List<Livro>> findByClassificacaoLess(
            @ApiParam(value = "Classificação indicativa máxima", required = true) @PathVariable int i) {
        List<Livro> livros = livroServices.findByClassificacaoIndicativaisLessThanEqual(i);
        return ResponseEntity.ok(livros);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Buscar livro por ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Livro encontrado com sucesso"),
            @ApiResponse(code = 404, message = "Livro não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno no servidor")
    })
    public ResponseEntity<Livro> findById(
            @ApiParam(value = "ID do livro", required = true) @PathVariable long id) {
        Livro livro = livroServices.findById(id);
        return ResponseEntity.ok(livro);
    }

    @GetMapping("/nome/{name}")
    @ApiOperation(value = "Buscar livro por nome")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Livro encontrado com sucesso"),
            @ApiResponse(code = 404, message = "Livro não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno no servidor")
    })
    public ResponseEntity<Livro> findByName(
            @ApiParam(value = "Nome do livro", required = true) @PathVariable String name) {
        Livro livro = livroServices.findByName(name);
        return ResponseEntity.ok(livro);
    }

    @GetMapping("/genero/{genero}")
    @ApiOperation(value = "Buscar livros por gênero")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Livros encontrados com sucesso"),
            @ApiResponse(code = 500, message = "Erro interno no servidor")
    })
    public ResponseEntity<List<Livro>> findByGenero(
            @ApiParam(value = "Gênero do livro", required = true) @PathVariable Genero genero) {
        List<Livro> livros = livroServices.findListByGenero(genero);
        return ResponseEntity.ok(livros);
    }

    @PostMapping
    @ApiOperation(value = "Registrar livro")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Livro registrado com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida"),
            @ApiResponse(code = 500, message = "Erro interno no servidor")
    })
    public ResponseEntity<Livro> registerLivro(
            @ApiParam(value = "Dados do livro", required = true) @RequestBody Livro livro,
            UriComponentsBuilder uriBuilder) {
        Livro livrosSalvo = livroServices.register(livro);
        if (livrosSalvo != null) {
            URI uri = uriBuilder.path("/ClientePF/{id}").buildAndExpand(livrosSalvo.getId()).toUri();
            return ResponseEntity.created(uri).body(livrosSalvo);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/update")
    @ApiOperation(value = "Atualizar livro")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Livro atualizado com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida"),
            @ApiResponse(code = 404, message = "Livro não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno no servidor")
    })
    public ResponseEntity<Livro> updateLivro(
            @ApiParam(value = "Dados de atualização do livro", required = true) @RequestBody UpdateLivroDTO data) {

        Livro updatedLivro = livroServices.update(data);
        return ResponseEntity.ok(updatedLivro);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletar livro por ID")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Livro deletado com sucesso"),
            @ApiResponse(code = 404, message = "Livro não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno no servidor")
    })
    public ResponseEntity<Void> deleteById(
            @ApiParam(value = "ID do livro", required = true) @PathVariable long id) {

        livroServices.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
