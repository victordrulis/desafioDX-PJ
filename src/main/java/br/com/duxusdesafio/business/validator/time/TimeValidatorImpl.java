package br.com.duxusdesafio.business.validator.time;

import br.com.duxusdesafio.business.exception.BusinessException;
import br.com.duxusdesafio.business.validator.api.BusinessValidator;
import br.com.duxusdesafio.business.validator.api.Validador;
import org.springframework.stereotype.Service;

@Service
public class TimeValidatorImpl extends BusinessValidator implements Validador {
    @Override
    public void validar(Object object) throws BusinessException {

    }
}
