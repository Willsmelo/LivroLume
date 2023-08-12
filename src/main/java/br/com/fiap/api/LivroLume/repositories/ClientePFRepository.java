package br.com.fiap.api.LivroLume.repositories;

import br.com.fiap.api.LivroLume.entities.ClientePF;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientePFRepository extends JpaRepository<ClientePF,Long> {
    Optional<ClientePF> findClientePFByNome(String nome);
    Optional<ClientePF> findClientePFByCPF(String cpf);
    Optional<ClientePF> findClientePFByRG(String nome);
}
