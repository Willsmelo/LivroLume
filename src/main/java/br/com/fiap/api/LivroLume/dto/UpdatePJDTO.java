package br.com.fiap.api.LivroLume.dto;

import br.com.fiap.api.LivroLume.enums.Plano;
import jakarta.validation.constraints.NotNull;

public class UpdatePJDTO {
    @NotNull
    private Long id;
    private String nome;
    private Plano plano;
    private String CNPJ;
    private String inscricao;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Plano getPlano() {
        return plano;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public String getInscricao() {
        return inscricao;
    }
}
