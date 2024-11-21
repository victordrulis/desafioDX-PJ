package br.com.duxusdesafio.view.time.validator;

import br.com.duxusdesafio.business.exception.BusinessException;
import br.com.duxusdesafio.business.model.Time;
import br.com.duxusdesafio.business.validator.api.ApiValidator;
import br.com.duxusdesafio.business.validator.api.BusinessValidator;
import br.com.duxusdesafio.view.time.TimeDto;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;
import static org.springframework.util.ObjectUtils.isEmpty;

@Component
public class TimeApiValidatorImpl extends ApiValidator implements TimeApiValidator {

    @Override
    public void validar(Time time) throws BusinessException {
    }

    @Override
    public void validarFormulario(TimeDto timeDto) throws BusinessException {

        if (isNull(timeDto)) {
            throw new BusinessException("O formulário de time está vazio.");
        } else if (isEmpty(timeDto.getDescricao())) {
            throw new BusinessException("O time não possui descrição.");
        }

        BusinessValidator.validarNulo((timeDto.getData()));
    }

}
