package br.com.fiap.api.LivroLume.services;

import br.com.fiap.api.LivroLume.dto.UpdatePFDTO;
import br.com.fiap.api.LivroLume.entities.ClientePF;
import br.com.fiap.api.LivroLume.entities.Livro;
import br.com.fiap.api.LivroLume.enums.Plano;
import br.com.fiap.api.LivroLume.exceptions.ClienteNaoEncontradoException;
import br.com.fiap.api.LivroLume.exceptions.IdadeParaRegistroNaLumeInvalida;
import br.com.fiap.api.LivroLume.exceptions.PlanoAbaixoDoNecessarioException;
import br.com.fiap.api.LivroLume.exceptions.QuantidadeMaximaDeLivrosAlugadosPorPessoaException;
import br.com.fiap.api.LivroLume.repositories.ClientePFRepository;
import br.com.fiap.api.LivroLume.repositories.LivroRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ClientePFServices {
    @Autowired
    private ClientePFRepository clientePFRepository;
    @Autowired
    LivroService livroService;


    public List<ClientePF> findAll() {
        return clientePFRepository.findAll();
    }

    public List<ClientePF> findByPlanoisGreaterThanEqual(Plano plano) {
        List<ClientePF> lista = new ArrayList<>();
        clientePFRepository.findAll().forEach(clientePF -> {
            if (clientePF.getPlano().getValor() >= plano.getValor()) {
                lista.add(clientePF);
            }
        });
        return lista;
    }

    public List<ClientePF> findByPlanoLessThanEqual(Plano plano) {
        List<ClientePF> lista = new ArrayList<>();
        clientePFRepository.findAll().forEach(clientePF -> {
            if (clientePF.getPlano().getValor() <= plano.getValor()) {
                lista.add(clientePF);
            }
        });
        return lista;
    }

    public ClientePF findById(long id) {
        return clientePFRepository.findById(id).orElseThrow(() -> new ClienteNaoEncontradoException("id"));
    }

    public ClientePF findByName(String name) {
        return clientePFRepository.findClientePFByNome(name).orElseThrow(() -> new ClienteNaoEncontradoException("nome"));
    }

    public ClientePF findByCPF(String CPF) {
        return clientePFRepository.findClientePFByCPF(CPF).orElseThrow(() -> new ClienteNaoEncontradoException("CPF"));
    }

    public ClientePF findByRG(String RG) {
        return clientePFRepository.findClientePFByRG(RG).orElseThrow(() -> new ClienteNaoEncontradoException("RG"));
    }

    public ClientePF register(@Valid ClientePF cliente) {
        if (cliente.getIdade() < 12) throw new IdadeParaRegistroNaLumeInvalida();
        return clientePFRepository.save(cliente);
    }

    public ClientePF update(UpdatePFDTO data) {
        ClientePF clienteToBeUpdate = findById(data.getId());
        return clienteToBeUpdate.updateData(data);
    }

    public ClientePF upgradePlano(long id) {
        ClientePF clientePF = findById(id);
        clientePF.setPlano(clientePF.getPlano().proximoPlano());
        return clientePF;
    }

    public ClientePF downgradePlano(long id) {
        ClientePF clientePF = findById(id);
        clientePF.setPlano(clientePF.getPlano().planoAtras());
        return clientePF;
    }

    public void deleteById(long id) {
        findById(id);
        clientePFRepository.deleteById(id);
    }

    public Set<Livro> adicionar(Long id, Long idCliente) {
        ClientePF clientePF = findById(idCliente);

        if (!clientePF.validadorDeLivrosMaximosAlugados())
            throw new QuantidadeMaximaDeLivrosAlugadosPorPessoaException();

        if (clientePF.getPlano().getValor() < livroService.findById(id).getPlanoDisponivel().getValor())
            throw new PlanoAbaixoDoNecessarioException();

        clientePF.getLivrosAlugados().add(livroService.findById(id));

        return clientePF.getLivrosAlugados();
    }

    public Set<Livro> remover(Long id, Long idCliente) {
        ClientePF clientePF = findById(idCliente);

        clientePF.getLivrosAlugados().forEach(livro -> {
            if (livro.getId() == id) clientePF.getLivrosAlugados().remove(livro);
        });
        return clientePF.getLivrosAlugados();
    }


}
