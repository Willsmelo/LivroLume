package br.com.fiap.api.LivroLume.services;

import br.com.fiap.api.LivroLume.dto.UpdatePJDTO;
import br.com.fiap.api.LivroLume.entities.ClientePJ;
import br.com.fiap.api.LivroLume.entities.ClientePJ;
import br.com.fiap.api.LivroLume.entities.ClientePJ;
import br.com.fiap.api.LivroLume.entities.Livro;
import br.com.fiap.api.LivroLume.enums.Plano;
import br.com.fiap.api.LivroLume.exceptions.ClienteNaoEncontradoException;
import br.com.fiap.api.LivroLume.exceptions.PlanoAbaixoDoNecessarioException;
import br.com.fiap.api.LivroLume.exceptions.QuantidadeMaximaDeLivrosAlugadosPorPessoaException;
import br.com.fiap.api.LivroLume.repositories.ClientePJRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ClientePJServices {
    @Autowired
    private ClientePJRepository clientePJRepository;
    @Autowired LivroService livroService;


    public List<ClientePJ> findAll(){
        return clientePJRepository.findAll();
    }

    public List<ClientePJ> findByPlanoGreaterThanEqual(Plano plano){
        List<ClientePJ> lista = new ArrayList<>();
        clientePJRepository.findAll().forEach(clientePJ -> {
            if (clientePJ.getPlano().getValor() >= plano.getValor()) {
                lista.add(clientePJ);
            }
        });
        return lista;
    }

    public List<ClientePJ> findByPlanoLessThanEqual(Plano plano){
        List<ClientePJ> lista = new ArrayList<>();
        clientePJRepository.findAll().forEach(clientePJ -> {
            if (clientePJ.getPlano().getValor() <= plano.getValor()) {
                lista.add(clientePJ);
            }
        });
        return lista;
    }

    public ClientePJ findById(long id){
        return clientePJRepository.findById(id).orElseThrow(() -> new ClienteNaoEncontradoException("id"));
    }

    public ClientePJ findByName(String name){
        return clientePJRepository.findClientePJByNome(name).orElseThrow(() -> new ClienteNaoEncontradoException("nome"));
    }
    public ClientePJ findByCNPJ(String CNPJ){
        return clientePJRepository.findClientePJByCNPJ(CNPJ).orElseThrow(() -> new ClienteNaoEncontradoException("CNPJ"));
    }
    public ClientePJ findByInscricao(String inscricao){
        return clientePJRepository.findClientePJByInscricaoEstadual(inscricao).orElseThrow(() -> new ClienteNaoEncontradoException("Inscricao Estadual"));
    }

    public ClientePJ register(@Valid ClientePJ cliente){
        return clientePJRepository.save(cliente);
    }

    public ClientePJ update(UpdatePJDTO data){
        ClientePJ clienteToBeUpdate = findById(data.getId());
        return clienteToBeUpdate.updateData(data);
    }

    public ClientePJ upgradePlano(long id){
        ClientePJ clientePJ = findById(id);
        clientePJ.setPlano(clientePJ.getPlano().proximoPlano());
        return clientePJ;
    }
    public ClientePJ downgradePlano(long id){
        ClientePJ clientePJ = findById(id);
        clientePJ.setPlano(clientePJ.getPlano().planoAtras());
        return clientePJ;
    }
    public void deleteById(long id){
        findById(id);
        deleteById(id);
    }

    public Set<Livro> adicionar(Long id, Long idCliente) {
        ClientePJ clientePJ = findById(idCliente);

        if (!clientePJ.validadorDeLivrosMaximosAlugados())
            throw new QuantidadeMaximaDeLivrosAlugadosPorPessoaException();

        if (clientePJ.getPlano().getValor() < livroService.findById(id).getPlanoDisponivel().getValor())
            throw new PlanoAbaixoDoNecessarioException();

        clientePJ.getLivrosAlugados().add(livroService.findById(id));

        return clientePJ.getLivrosAlugados();
    }

    public Set<Livro> remover(Long id, Long idCliente) {
        ClientePJ clientePJ = findById(idCliente);

        clientePJ.getLivrosAlugados().forEach(livro -> {
            if (livro.getId() == id) clientePJ.getLivrosAlugados().remove(livro);
        });
        return clientePJ.getLivrosAlugados();
    }
    }

