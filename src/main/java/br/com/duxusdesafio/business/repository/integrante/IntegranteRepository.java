package br.com.duxusdesafio.business.repository.integrante;

import br.com.duxusdesafio.business.model.Integrante;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntegranteRepository extends PagingAndSortingRepository<Integrante, Long> {
}
