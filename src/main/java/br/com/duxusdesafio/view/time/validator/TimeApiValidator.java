package br.com.duxusdesafio.view.time.validator;

import br.com.duxusdesafio.business.exception.BusinessException;
import br.com.duxusdesafio.business.model.Time;
import br.com.duxusdesafio.view.time.TimeDto;

public interface TimeApiValidator {
    void validar(Time time) throws BusinessException;
    void validarFormulario(TimeDto timeDto) throws BusinessException;
}
