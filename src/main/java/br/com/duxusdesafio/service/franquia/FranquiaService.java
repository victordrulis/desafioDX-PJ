package br.com.duxusdesafio.service.franquia;

import br.com.duxusdesafio.business.exception.BusinessException;
import br.com.duxusdesafio.business.model.Franquia;
import br.com.duxusdesafio.view.franquia.FranquiaDto;

import java.util.List;

public interface FranquiaService {
    void salvar(FranquiaDto timeDto) throws BusinessException;
    List<Franquia> obterTodos() throws BusinessException;
    FranquiaDto obter(Long id) throws BusinessException;
    void excluir(Long id) throws BusinessException;
}
