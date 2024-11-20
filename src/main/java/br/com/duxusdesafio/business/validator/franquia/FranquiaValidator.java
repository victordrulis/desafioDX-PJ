package br.com.duxusdesafio.business.validator.franquia;

import br.com.duxusdesafio.business.exception.BusinessException;
import br.com.duxusdesafio.business.model.Franquia;
import br.com.duxusdesafio.view.franquia.FranquiaDto;

public interface FranquiaValidator {
    public void validar(Franquia franquia) throws BusinessException;
    public void validarFormulario(FranquiaDto franquiaDto) throws BusinessException;
}
