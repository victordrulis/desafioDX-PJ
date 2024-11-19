package br.com.duxusdesafio.business.validator.time;

import br.com.duxusdesafio.business.exception.BusinessException;
import br.com.duxusdesafio.business.model.Time;
import br.com.duxusdesafio.view.time.TimeDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TimeApiValidatorImpl implements TimeValidator {

    @Override
    public void validar(Time time) throws BusinessException {
    }

    @Override
    public void validarFormulario(TimeDto timeDto) throws BusinessException {
        throw new BusinessException("Não é possível validar formularios de time.");
    }

}
