package br.com.duxusdesafio.business.validator.funcao;

import br.com.duxusdesafio.business.exception.BusinessException;
import br.com.duxusdesafio.business.validator.api.BusinessValidator;
import br.com.duxusdesafio.business.validator.api.Validador;
import org.springframework.stereotype.Component;

@Component
public class FuncaoExcluirValidatorImpl extends BusinessValidator implements Validador {

    @Override
    public void validar(Object object) throws BusinessException {

    }
}
