package br.com.duxusdesafio.view.composicaotime;

import br.com.duxusdesafio.business.model.ComposicaoTime;
import br.com.duxusdesafio.view.integrante.IntegranteDto;
import br.com.duxusdesafio.view.time.TimeDto;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.apache.commons.lang3.ObjectUtils.isEmpty;

@Data
@Builder
public class ComposicaoTimeDto {

    private Long id;
    private List<IntegranteDto> listaIntegrante;
    private TimeDto time;

    public static ComposicaoTimeDto from(ComposicaoTime composicaoTime) {
        ComposicaoTimeDto composicaoTimeDto = builder()
                .id(composicaoTime.getId())
                .time(TimeDto.from(composicaoTime.getTime()))
                .build();

        composicaoTimeDto.addIntegrante(IntegranteDto.from(composicaoTime.getIntegrante()));

        return composicaoTimeDto;
    }

    public void addIntegrante(IntegranteDto integranteDto) {
        if(isEmpty(listaIntegrante)) {
            listaIntegrante = new ArrayList<>();
        }

        listaIntegrante.add(integranteDto);
    }
}
