package br.com.duxusdesafio.business.repository.time;

import br.com.duxusdesafio.business.model.Time;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeRepository extends PagingAndSortingRepository<Time, Long> {
}
