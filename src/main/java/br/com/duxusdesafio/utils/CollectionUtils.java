package br.com.duxusdesafio.utils;

import br.com.duxusdesafio.business.exception.BusinessException;

import java.util.List;

public abstract class CollectionUtils {

    public static void validarListaVazia(List<?> colecao) throws BusinessException {
        if(org.springframework.util.CollectionUtils.isEmpty(colecao)) {
            throw new BusinessException("A lista esta vazia.");
        }
    }
}
