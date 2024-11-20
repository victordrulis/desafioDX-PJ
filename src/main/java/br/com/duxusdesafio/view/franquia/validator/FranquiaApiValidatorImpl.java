package br.com.duxusdesafio.view.franquia.validator;

import br.com.duxusdesafio.business.exception.BusinessException;
import br.com.duxusdesafio.business.model.Franquia;
import br.com.duxusdesafio.view.franquia.FranquiaDto;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;
import static org.springframework.util.ObjectUtils.isEmpty;

@Component
public class FranquiaApiValidatorImpl implements FranquiaApiValidator {

    @Override
    public void validar(Franquia franquia) throws BusinessException {
        
    }

    @Override
    public void validarFormulario(FranquiaDto franquiaDto) throws BusinessException {
        if (isNull(franquiaDto)) {
            throw new BusinessException("O formulário de franquia está vazio.");
        } else if (isEmpty(franquiaDto.getNome())) {
            throw new BusinessException("O franquia não possui nome.");
        }
    }
}
