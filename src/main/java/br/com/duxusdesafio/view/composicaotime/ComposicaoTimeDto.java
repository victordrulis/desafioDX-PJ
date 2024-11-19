package br.com.duxusdesafio.view.composicaotime;

import br.com.duxusdesafio.view.integrante.IntegranteDto;
import br.com.duxusdesafio.view.time.TimeDto;
import lombok.Data;

import java.util.Set;

@Data
public class ComposicaoTimeDto {

    private Set<IntegranteDto> listaIntegrante;
    private TimeDto time;
}
