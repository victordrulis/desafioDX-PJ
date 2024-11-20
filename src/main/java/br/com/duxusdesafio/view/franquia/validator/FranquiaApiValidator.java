package br.com.duxusdesafio.view.franquia.validator;

import br.com.duxusdesafio.business.exception.BusinessException;
import br.com.duxusdesafio.business.model.Franquia;
import br.com.duxusdesafio.view.franquia.FranquiaDto;

public interface FranquiaApiValidator {
    void validar(Franquia franquia) throws BusinessException;
    void validarFormulario(FranquiaDto timeDto) throws BusinessException;
}
