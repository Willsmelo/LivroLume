package br.com.fiap.api.LivroLume.dto;

import br.com.fiap.api.LivroLume.entities.Cliente;
import br.com.fiap.api.LivroLume.enums.Genero;
import jakarta.validation.constraints.NotNull;

public class UpdateLivroDTO {
    @NotNull
    private Long id;
    private String nome;
    private Integer classificacaoIndicativa;
    private Genero genero;
    private Cliente posseDoLivro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getClassificacaoIndicativa() {
        return classificacaoIndicativa;
    }

    public void setClassificacaoIndicativa(Integer classificacaoIndicativa) {
        this.classificacaoIndicativa = classificacaoIndicativa;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Cliente getPosseDoLivro() {
        return posseDoLivro;
    }

    public void setPosseDoLivro(Cliente posseDoLivro) {
        this.posseDoLivro = posseDoLivro;
    }
}
