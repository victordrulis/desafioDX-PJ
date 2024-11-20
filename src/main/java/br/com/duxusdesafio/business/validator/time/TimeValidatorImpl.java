package br.com.duxusdesafio.business.validator.time;

import br.com.duxusdesafio.business.exception.BusinessException;
import br.com.duxusdesafio.business.model.Time;
import br.com.duxusdesafio.business.validator.api.BusinessValidator;
import br.com.duxusdesafio.view.time.TimeDto;
import org.springframework.stereotype.Service;

@Service
public class TimeValidatorImpl extends BusinessValidator implements TimeValidator {
    @Override
    public void validar(Time time) throws BusinessException {

    }

    @Override
    public void validarFormulario(TimeDto timeDto) throws BusinessException {

    }


}
