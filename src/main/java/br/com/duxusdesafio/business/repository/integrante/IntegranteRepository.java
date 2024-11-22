package br.com.duxusdesafio.business.repository.integrante;

import br.com.duxusdesafio.business.model.Franquia;
import br.com.duxusdesafio.business.model.Integrante;
import org.springframework.beans.PropertyValues;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntegranteRepository extends PagingAndSortingRepository<Integrante, Long> {
    List<Integrante> findByFranquiaIn(List<Franquia> franquias);
}
