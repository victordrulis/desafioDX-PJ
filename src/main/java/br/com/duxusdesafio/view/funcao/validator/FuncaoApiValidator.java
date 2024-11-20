package br.com.duxusdesafio.view.funcao.validator;

import br.com.duxusdesafio.business.exception.BusinessException;
import br.com.duxusdesafio.business.model.Funcao;
import br.com.duxusdesafio.view.funcao.FuncaoDto;

public interface FuncaoApiValidator {
    void validar(Funcao funcao) throws BusinessException;
    void validarFormulario(FuncaoDto timeDto) throws BusinessException;
}
