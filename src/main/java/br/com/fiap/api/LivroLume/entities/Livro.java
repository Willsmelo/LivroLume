package br.com.fiap.api.LivroLume.entities;

import br.com.fiap.api.LivroLume.dto.UpdateLivroDTO;
import br.com.fiap.api.LivroLume.enums.Genero;
import br.com.fiap.api.LivroLume.enums.Plano;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "TB_LIVRO")

public class Livro {

    @Id
    @SequenceGenerator(name = "SQ_LIVRO", sequenceName = "SQ_LIVRO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SQ_LIVRO")
    @Column(name = "ID_LIVRO")
    private Long id;
    @Column(name = "NOME_LIVRO")
    @NotBlank
    private String nome;
    @Column(name = "CLASSIFICACAO_LIVRO")
    @NotNull
    private Integer classificacaoIndicativa;
    @Column(name = "GENERO_LIVRO")
    @NotNull
    @Enumerated(EnumType.STRING)
    private Genero genero;
    @Column(name = "PLANO_DISPONIVEL")
    private Plano planoDisponivel;
    @Column(name = "AUTOR_LIVRO")
    private String autor;

    public Livro() {
    }

    public Livro(Long id, String nome, Integer classificacaoIndicativa, Genero genero, Plano planoDisponivel, String autor) {
        this.id = id;
        this.nome = nome;
        this.classificacaoIndicativa = classificacaoIndicativa;
        this.genero = genero;
        this.planoDisponivel = planoDisponivel;
        this.autor = autor;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String name) {
        this.nome = name;
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

    public Livro updateData(UpdateLivroDTO data) {
            if (data.getNome() != null) setNome(data.getNome());
            if (data.getClassificacaoIndicativa() != null) setClassificacaoIndicativa(data.getClassificacaoIndicativa());
            if (data.getGenero() != null) setGenero(data.getGenero());

            return this;
    }


    public Plano getPlanoDisponivel() {
        return planoDisponivel;
    }

    public void setPlanoDisponivel(Plano planoDisponivel) {
        this.planoDisponivel = planoDisponivel;
    }


    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", classificacaoIndicativa=" + classificacaoIndicativa +
                ", genero=" + genero +
                ", planoDisponivel=" + planoDisponivel +
                ", autor='" + autor + '\'' +
                '}';
    }
}
