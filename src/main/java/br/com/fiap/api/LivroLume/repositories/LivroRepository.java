package br.com.fiap.api.LivroLume.repositories;
import br.com.fiap.api.LivroLume.entities.Livro;
import br.com.fiap.api.LivroLume.enums.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro,Long> {

    Optional<Livro> findLivroByNome(String nome);
    List<Livro> findLivrosByClassificacaoIndicativaIsGreaterThanEqual(int i);

    List<Livro> findLivroByClassificacaoIndicativaIsLessThanEqual(int i);
    List<Livro> findLivroByGeneroIs(Genero genero);
}
