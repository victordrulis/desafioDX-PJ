package br.com.duxusdesafio.business.repository.franquia;

import br.com.duxusdesafio.business.model.Franquia;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FranquiaRepository extends PagingAndSortingRepository<Franquia, Long> {
}
