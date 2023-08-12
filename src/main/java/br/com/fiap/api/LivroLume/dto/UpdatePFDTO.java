package br.com.fiap.api.LivroLume.dto;
import br.com.fiap.api.LivroLume.enums.Plano;
import jakarta.validation.constraints.NotNull;

public class UpdatePFDTO {

    @NotNull
    private Long id;
    private String nome;
    private Plano plano;
    private String CPF;
    private String RG;
    private Integer idade;


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

    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
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

    public void setIdade(Integer idade) {
        this.idade = idade;
    }
}
