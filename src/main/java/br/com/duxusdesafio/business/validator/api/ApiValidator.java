package br.com.duxusdesafio.business.validator.api;

import br.com.duxusdesafio.business.exception.BusinessException;
import br.com.duxusdesafio.business.model.Time;
import br.com.duxusdesafio.utils.CollectionUtils;
import br.com.duxusdesafio.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Component
public class ApiValidator {
    private final Logger LOGGER = LoggerFactory.getLogger(ApiValidator.class);

    /**
     * Valida os argumentos para retornar um Time, com a composição do time daquela data
     */
    public void validarTimeDaData(LocalDate data, List<Time> todosOsTimes) throws BusinessException {
        try {
            DateUtils.validarDataAposDataAtual(data, LocalDate.now());
            CollectionUtils.validarListaVazia(todosOsTimes);
        } catch (BusinessException exception) {
            BusinessException businessException = new BusinessException("Argumentos inválidos na busca de time na data.", exception);
            LOGGER.error(businessException.getMessage(), exception);
            throw businessException;
        }
    }
}
