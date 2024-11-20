package br.com.duxusdesafio.view.integrante.validator;

import br.com.duxusdesafio.business.exception.BusinessException;
import br.com.duxusdesafio.business.model.Integrante;
import br.com.duxusdesafio.view.integrante.IntegranteDto;
import br.com.duxusdesafio.view.integrante.validator.IntegranteApiValidator;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;
import static org.springframework.util.ObjectUtils.isEmpty;

@Component
public class IntegranteApiValidatorImpl implements IntegranteApiValidator {

    @Override
    public void validar(Integrante integrante) throws BusinessException {
        
    }

    @Override
    public void validarFormulario(IntegranteDto integranteDto) throws BusinessException {
        if (isNull(integranteDto)) {
            throw new BusinessException("O formulário de função está vazio.");
        } else if (isEmpty(integranteDto.getNome())) {
            throw new BusinessException("A função não possui nome.");
        }
    }
}
