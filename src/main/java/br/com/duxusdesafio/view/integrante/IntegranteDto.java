package br.com.duxusdesafio.view.integrante;

import br.com.duxusdesafio.business.model.ComposicaoTime;
import br.com.duxusdesafio.business.model.Integrante;
import br.com.duxusdesafio.view.composicaotime.ComposicaoTimeDto;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class IntegranteDto implements Serializable {
    private Long id;
    private String nome;
    private Long franquiaId;
    private Long funcaoId;
    private List<ComposicaoTimeDto> composicaoTimes;

    public static IntegranteDto from(Integrante integrante) {
        return builder()
                .nome(integrante.getNome())
                .franquiaId(integrante.getFranquia().getId())
                .funcaoId(integrante.getFuncao().getId())
                .composicaoTimes(integrante.getComposicaoTimes().stream()
                        .map(ComposicaoTimeDto::from)
                        .collect(Collectors.toList()))
                .build();
    }
}
