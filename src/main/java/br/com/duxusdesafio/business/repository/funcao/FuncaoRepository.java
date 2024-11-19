package br.com.duxusdesafio.business.repository.funcao;

import br.com.duxusdesafio.business.model.Funcao;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncaoRepository extends PagingAndSortingRepository<Funcao, Long> {
}
