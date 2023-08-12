package br.com.fiap.api.LivroLume.exceptions;

public class ClienteNaoEncontradoException extends RuntimeException{
    public ClienteNaoEncontradoException(String parametro){
        super("Nao foi encontrado um cliente com esse " + parametro);
    }
}
