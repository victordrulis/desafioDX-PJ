package br.com.duxusdesafio.business.validator.time;

import br.com.duxusdesafio.business.model.Time;
import br.com.duxusdesafio.view.time.TimeDto;

public interface TimeValidator {
    boolean validar(Time time);
    boolean validarFormulario(TimeDto timeDto);
}
