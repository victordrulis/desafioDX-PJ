package br.com.duxusdesafio.business.validator.funcao;

import br.com.duxusdesafio.business.exception.BusinessException;
import br.com.duxusdesafio.business.model.Funcao;
import br.com.duxusdesafio.view.funcao.FuncaoDto;

public interface FuncaoValidator {
    public void validar(Funcao funcao) throws BusinessException;
    public void validarFormulario(FuncaoDto funcaoDto) throws BusinessException;
}
