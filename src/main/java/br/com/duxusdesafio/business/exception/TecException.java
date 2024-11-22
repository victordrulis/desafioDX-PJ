package br.com.duxusdesafio.business.exception;

public class TecException extends RuntimeException {

    public TecException(String mensagemErro) {
        super(mensagemErro);
    }

    public TecException(String mensagemErro, Throwable causa) {
        super(mensagemErro, causa);
    }
}
