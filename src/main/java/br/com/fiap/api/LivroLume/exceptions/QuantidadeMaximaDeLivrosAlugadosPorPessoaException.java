package br.com.fiap.api.LivroLume.exceptions;

public class QuantidadeMaximaDeLivrosAlugadosPorPessoaException extends RuntimeException{
    public QuantidadeMaximaDeLivrosAlugadosPorPessoaException(){
        super("Voce ja alugou o numero maximo de livros permitidos!");
    }
}
