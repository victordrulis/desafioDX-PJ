package br.com.duxusdesafio.business.validator.funcao;

import br.com.duxusdesafio.business.exception.BusinessException;
import br.com.duxusdesafio.business.model.Funcao;
import br.com.duxusdesafio.business.validator.api.BusinessValidator;
import br.com.duxusdesafio.business.validator.funcao.FuncaoValidator;
import br.com.duxusdesafio.view.funcao.FuncaoDto;
import org.springframework.stereotype.Component;

@Component
public class FuncaoValidatorImpl extends BusinessValidator implements FuncaoValidator {

    @Override
    public void validar(Funcao funcao) throws BusinessException {

    }

    @Override
    public void validarFormulario(FuncaoDto funcaoDto) throws BusinessException {

    }


}
