package br.com.fiap.api.LivroLume.exceptions;

public class PlanoAbaixoDoNecessarioException extends RuntimeException{
    public PlanoAbaixoDoNecessarioException(){
        super("Esse plano nao e suficiente para que voce alugue esse livro, faça um upgrade em seu plano ou aguarde o catalogo de seu plano atualizar!");
    }
}
