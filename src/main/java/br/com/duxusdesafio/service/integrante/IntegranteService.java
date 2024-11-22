package br.com.duxusdesafio.service.integrante;

import br.com.duxusdesafio.business.exception.BusinessException;
import br.com.duxusdesafio.business.model.Integrante;
import br.com.duxusdesafio.view.integrante.IntegranteDto;

import java.util.List;

public interface IntegranteService {
    void salvar(IntegranteDto timeDto) throws BusinessException;
    List<Integrante> obterTodos() throws BusinessException;
    IntegranteDto obter(Long id) throws BusinessException;
    void excluir(Long id) throws BusinessException;
}
