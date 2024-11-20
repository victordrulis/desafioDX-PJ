package br.com.duxusdesafio.service.time;

import br.com.duxusdesafio.business.exception.BusinessException;
import br.com.duxusdesafio.business.model.Time;
import br.com.duxusdesafio.view.time.TimeDto;

import java.time.LocalDate;
import java.util.List;

public interface TimeService {
    void salvar(TimeDto timeDto) throws BusinessException;
    List<Time> obterTodos() throws BusinessException;
    List<Time> obterTodosPorData(LocalDate data) throws BusinessException;
    TimeDto obter(Long id) throws BusinessException;
    void excluir(Long id) throws BusinessException;
}
