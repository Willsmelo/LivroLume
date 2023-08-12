package br.com.fiap.api.LivroLume.exceptions;

public class IdadeParaRegistroNaLumeInvalida extends RuntimeException{
    public IdadeParaRegistroNaLumeInvalida(){
        super("Voce so pode se registrar na biblioteca lume a partir dos 12 anos de idade!");
    }
}
