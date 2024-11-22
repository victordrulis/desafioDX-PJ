package br.com.duxusdesafio.business.validator.integrante;

import br.com.duxusdesafio.business.exception.BusinessException;
import br.com.duxusdesafio.business.validator.api.BusinessValidator;
import br.com.duxusdesafio.business.validator.api.Validador;
import org.springframework.stereotype.Component;

@Component
public class IntegranteValidatorImpl extends BusinessValidator implements Validador {
    @Override
    public void validar(Object object) throws BusinessException {

    }
}
