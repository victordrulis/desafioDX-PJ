package br.com.duxusdesafio.service.time;

import br.com.duxusdesafio.business.model.Time;
import br.com.duxusdesafio.business.repository.time.TimeRepository;
import br.com.duxusdesafio.view.time.TimeDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeServiceImpl implements TimeService {

    private final TimeRepository repository;

    public TimeServiceImpl(TimeRepository repository) {
        this.repository = repository;
    }

    @Override
    public void salvar(TimeDto timeDto) {
        Time time = Time.builder()
                .descricao(timeDto.getDescricao())
                .build();

        repository.save(time);

    }

    @Override
    public List<Time> obterTodos() {
        return null;
    }
}
