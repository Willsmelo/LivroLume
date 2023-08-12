package br.com.fiap.api.LivroLume.services;

import br.com.fiap.api.LivroLume.entities.Livro;
import br.com.fiap.api.LivroLume.exceptions.LivroNaoEncontradoException;
import br.com.fiap.api.LivroLume.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LivroService {
    @Autowired
    LivroRepository livroRepository;
    public Livro findById(Long id){
        return livroRepository.findById(id).orElseThrow(() -> new LivroNaoEncontradoException("id"));
    }
}
