package br.com.duxusdesafio.service.time;

import br.com.duxusdesafio.business.model.Time;
import br.com.duxusdesafio.view.time.TimeDto;

import java.util.List;

public interface TimeService {

    void salvar(TimeDto timeDto);
    List<Time> obterTodos();
}
