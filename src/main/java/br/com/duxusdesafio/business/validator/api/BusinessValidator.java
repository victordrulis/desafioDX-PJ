package br.com.duxusdesafio.business.validator.api;

import br.com.duxusdesafio.business.exception.BusinessException;

import java.util.Optional;

public abstract class BusinessValidator {

    public static final String ARGUMENTO_VAZIO = "Argumento vazio.";
    public static final String OBJETO_NAO_EXISTE = "O objeto não existe";

    public static void validarNulo(Object object) {
        validarNulo(object, ARGUMENTO_VAZIO);
    }
    public static void validarNulo(Object object, String mensagem) {
        Optional.ofNullable(object)
                .orElseThrow(() -> new BusinessException(mensagem));
    }

    public static void validarObjetoExiste(Object object) {
        validarNulo(object, OBJETO_NAO_EXISTE);
    }
}
