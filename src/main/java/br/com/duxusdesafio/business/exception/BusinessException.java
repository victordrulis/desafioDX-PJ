package br.com.duxusdesafio.business.exception;

public class BusinessException extends Exception {

    public BusinessException() {
        super();
    }

    public BusinessException(String mensagemErro) {
        super(mensagemErro);
    }

    public BusinessException(String mensagemErro, Throwable causa) {
        super(mensagemErro, causa);
    }

}
