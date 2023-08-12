package br.com.fiap.api.LivroLume.services;

import br.com.fiap.api.LivroLume.dto.UpdateLivroDTO;
import br.com.fiap.api.LivroLume.entities.ClientePF;
import br.com.fiap.api.LivroLume.entities.Livro;
import br.com.fiap.api.LivroLume.enums.Genero;
import br.com.fiap.api.LivroLume.exceptions.ClienteNaoEncontradoException;
import br.com.fiap.api.LivroLume.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroServices {
    @Autowired
    LivroRepository repository;

    public List<Livro> findAll(){
        return repository.findAll();
    }
    public List<Livro> findByClassificacaoIndicativaisGreaterThanEqual(int i){
        return repository.findLivrosByClassificacaoIndicativaIsGreaterThanEqual(i);
    }
    public List<Livro> findByClassificacaoIndicativaisLessThanEqual(int i){
        return repository.findLivroByClassificacaoIndicativaIsLessThanEqual(i);
    }
    public Livro findById(long id){
        return repository.findById(id).orElseThrow(() -> new ClienteNaoEncontradoException("id"));
    }

    public Livro findByName(String name){
        return repository.findLivroByNome(name).orElseThrow(() -> new ClienteNaoEncontradoException("nome"));
    }

    public List<Livro> findListByGenero(Genero genero){
        return repository.findLivroByGeneroIs(genero);
    }

    public Livro update(UpdateLivroDTO data){
        Livro livroToBeUpdated = findById(data.getId());
        return livroToBeUpdated.updateData(data);
    }

    public void deleteById(long id){
        findById(id);
        repository.deleteById(id);
    }

    public Livro register(Livro livro){
        return repository.save(livro);
    }


}
