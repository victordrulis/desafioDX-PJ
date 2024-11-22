package br.com.duxusdesafio.service.composicao;

import br.com.duxusdesafio.view.composicaotime.ComposicaoTimeDto;
import br.com.duxusdesafio.view.integrante.IntegranteDto;
import br.com.duxusdesafio.view.time.TimeDto;

import java.util.Set;

public interface ComposicaoTimeService {

    ComposicaoTimeDto salvar(TimeDto time, Set<IntegranteDto> integranteSet);
    boolean encerrar(ComposicaoTimeDto composicaoTimeDto);

}
