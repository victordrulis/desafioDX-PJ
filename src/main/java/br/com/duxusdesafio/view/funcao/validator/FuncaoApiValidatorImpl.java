package br.com.duxusdesafio.view.funcao.validator;

import br.com.duxusdesafio.business.exception.BusinessException;
import br.com.duxusdesafio.business.model.Funcao;
import br.com.duxusdesafio.view.funcao.FuncaoDto;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;
import static org.springframework.util.ObjectUtils.isEmpty;

@Component
public class FuncaoApiValidatorImpl implements FuncaoApiValidator {

    @Override
    public void validar(Funcao funcao) throws BusinessException {
        
    }

    @Override
    public void validarFormulario(FuncaoDto funcaoDto) throws BusinessException {
        if (isNull(funcaoDto)) {
            throw new BusinessException("O formulário de funcao está vazio.");
        } else if (isEmpty(funcaoDto.getNome())) {
            throw new BusinessException("O funcao não possui nome.");
        }
    }
}
