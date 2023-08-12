package br.com.fiap.api.LivroLume.entities;

import br.com.fiap.api.LivroLume.dto.UpdatePFDTO;
import br.com.fiap.api.LivroLume.enums.Plano;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;


@Entity
@Table(name = "TB_CLIENTE_PF")
@DiscriminatorValue("PF")
public class ClientePF extends Cliente {

    @Column(name = "CLIENTE_CPF")
    @NotNull
    String CPF;
    @NotBlank
    @Column(name = "CLIENTE_RG")
    String RG;
    @NotNull
    @Column(name = "CLIENTE_IDADE")
    Integer idade;


    public ClientePF(Long id, String nome,Plano plano, Set<Livro> livrosAlugados, String CPF, String RG, Integer idade) {
        super(id, nome, plano, livrosAlugados);
        this.CPF = CPF;
        this.RG = RG;
        this.idade = idade;
    }

    public ClientePF() {
    }

    @Override
    public boolean validadorDeLivrosMaximosAlugados() {
        return this.getLivrosAlugados().size() <= 3;
    }

    public ClientePF updateData(UpdatePFDTO data) {
        if (data.getNome() != null) setNome(data.getNome());
        if (data.getCPF() != null) setCPF(data.getCPF());
        if (data.getIdade() != null) setIdade(data.getIdade());
        if (data.getRG() != null) setRG(data.getRG());

        return this;
    }


    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getRG() {
        return RG;
    }

    public void setRG(String RG) {
        this.RG = RG;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer age) {
        this.idade = age;
    }

    @Override
    public String toString() {
        return "ClientePF{" +
                "CPF='" + CPF + '\'' +
                ", RG='" + RG + '\'' +
                ", age=" + idade +
                "} " + super.toString();
    }
}
