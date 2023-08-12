package br.com.fiap.api.LivroLume.entities;

import br.com.fiap.api.LivroLume.dto.UpdatePJDTO;
import br.com.fiap.api.LivroLume.enums.Plano;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.util.Set;


@Entity
@Table(name = "TB_CLIENTE_PJ")
@DiscriminatorValue("PJ")
public class ClientePJ extends Cliente {

    @Column(name = "CLIENTE_CNPJ")
    @NotNull
    private String CNPJ;
    @NotNull
    @Column(name = "CLIENTE_IE")
    private String inscricaoEstadual;

    public ClientePJ() {
    }

    public ClientePJ(Long id, String nome, Plano plano, Set<Livro> livrosAlugados, String CNPJ, String inscricaoEstadual) {
        super(id, nome, plano, livrosAlugados);
        this.CNPJ = CNPJ;
        this.inscricaoEstadual = inscricaoEstadual;
    }

    @Override
    public boolean validadorDeLivrosMaximosAlugados() {
        return this.getLivrosAlugados().size()<=10;
    }


    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }


    public ClientePJ updateData(UpdatePJDTO data) {
        if (data.getNome() != null) setNome(data.getNome());
        if (data.getCNPJ() != null) setCNPJ(data.getCNPJ());
        if (data.getInscricao() != null) setInscricaoEstadual(data.getInscricao());
        if (data.getCNPJ() != null) setCNPJ(data.getCNPJ());

        return this;
    }
}
