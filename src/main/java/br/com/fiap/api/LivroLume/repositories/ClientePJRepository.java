package br.com.fiap.api.LivroLume.repositories;

import br.com.fiap.api.LivroLume.entities.ClientePJ;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientePJRepository extends JpaRepository<ClientePJ,Long> {
    Optional<ClientePJ> findClientePJByNome(String nome);
    Optional<ClientePJ> findClientePJByCNPJ(String cnpj);
    Optional<ClientePJ> findClientePJByInscricaoEstadual(String inscricao);
}
