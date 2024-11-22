package br.com.duxusdesafio.business.validator.api;

import br.com.duxusdesafio.business.exception.BusinessException;

public interface Validador {
    void validar(Object object) throws BusinessException;
}
