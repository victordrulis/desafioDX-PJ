package br.com.duxusdesafio.service.funcao;

import br.com.duxusdesafio.business.exception.BusinessException;
import br.com.duxusdesafio.business.model.Funcao;
import br.com.duxusdesafio.view.funcao.FuncaoDto;

import java.util.List;

public interface FuncaoService {
    void salvar(FuncaoDto timeDto) throws BusinessException;
    List<Funcao> obterTodos() throws BusinessException;
    FuncaoDto obter(Long id) throws BusinessException;
    void excluir(Long id) throws BusinessException;
}
