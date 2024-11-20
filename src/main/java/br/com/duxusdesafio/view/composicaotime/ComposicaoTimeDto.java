package br.com.duxusdesafio.view.composicaotime;

import br.com.duxusdesafio.business.model.ComposicaoTime;
import br.com.duxusdesafio.view.integrante.IntegranteDto;
import br.com.duxusdesafio.view.time.TimeDto;
import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.util.CollectionUtils.isEmpty;

@Data
@Builder
public class ComposicaoTimeDto {

    private Set<IntegranteDto> listaIntegrante;
    private TimeDto time;

    public static ComposicaoTimeDto from(ComposicaoTime composicaoTime) {
        ComposicaoTimeDto composicaoTimeDto = builder()
                .time(TimeDto.from(composicaoTime.getTime()))
                .build();

        composicaoTimeDto.addIntegrante(IntegranteDto.from(composicaoTime.getIntegrante()));

        return composicaoTimeDto;
    }

    public void addIntegrante(IntegranteDto integranteDto) {
        if(isEmpty(listaIntegrante)) {
            listaIntegrante = new HashSet<>();
        }

        listaIntegrante.add(integranteDto);
    }
}
