package br.com.duxusdesafio.business.repository.composicaotime;

import br.com.duxusdesafio.business.model.ComposicaoTime;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComposicaoTimeRepository extends PagingAndSortingRepository<ComposicaoTime, Long> {
}
