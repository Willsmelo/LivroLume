package br.com.fiap.api.LivroLume.exceptions;

public class LivroNaoEncontradoException extends RuntimeException{
    public LivroNaoEncontradoException(String parametro){
        super("Nao foi encontrado um livro com esse " + parametro);
    }
}
