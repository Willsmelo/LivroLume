package br.com.fiap.api.LivroLume.entities;

import br.com.fiap.api.LivroLume.enums.Plano;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "TB_Cliente")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TP_Cliente")
public abstract class Cliente {

    @Id
    @SequenceGenerator(name = "SQ_PESSOA", sequenceName = "SQ_PESSOA", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SQ_PESSOA")
    @Column(name = "ID_CLIENTE")
    private Long id;
    @NotBlank
    @Column(name = "NOME_CLIENTE")
    private String nome;
    @NotNull
    @Column(name = "PLANO_CLIENTE")
    @Enumerated(EnumType.STRING)
    private Plano plano;
    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(
            name = "TB_LISTA_LIVROS",
            joinColumns = {
                    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID_CLIENTE",foreignKey = @ForeignKey(name = "FK_DONO"))
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ID_LIVRO", referencedColumnName = "ID_LIVRO",foreignKey = @ForeignKey(name = "FK_LIVRO"))
            })
    private Set<Livro> livrosAlugados = new LinkedHashSet<>();

    public Cliente(Long id, String nome, br.com.fiap.api.LivroLume.enums.Plano plano, Set<Livro> livrosAlugados) {
        this.id = id;
        this.nome = nome;
        this.plano = plano;
        this.livrosAlugados = livrosAlugados;
    }

    public Cliente() {
    }


    public abstract boolean validadorDeLivrosMaximosAlugados();


    public Long getId() {
        return id;
    }

    public Cliente setId(Long id) {
        this.id = id;
        return this;
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

    public void setPlano(Plano PLANO) {
        this.plano = PLANO;
    }

    public Set<Livro> getLivrosAlugados() {
        return livrosAlugados;
    }

    public void setLivrosAlugados(Set<Livro> livrosAlugados) {
        this.livrosAlugados = livrosAlugados;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", Plano=" + plano +
                ", livrosAlugados=" + livrosAlugados +
                '}';
    }
}
