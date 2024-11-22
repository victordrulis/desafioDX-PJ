package br.com.duxusdesafio.view.integrante.validator;

import br.com.duxusdesafio.business.exception.BusinessException;
import br.com.duxusdesafio.business.model.Integrante;
import br.com.duxusdesafio.view.integrante.IntegranteDto;

public interface IntegranteApiValidator {
    void validar(Integrante integrante) throws BusinessException;
    void validarFormulario(IntegranteDto timeDto) throws BusinessException;
}
