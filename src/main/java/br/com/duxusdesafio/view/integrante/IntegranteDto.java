package br.com.duxusdesafio.view.integrante;

import br.com.duxusdesafio.business.model.Integrante;
import br.com.duxusdesafio.view.composicaotime.ComposicaoTimeDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class IntegranteDto {
    private Long id;
    private String nome;
    private List<ComposicaoTimeDto> composicaoTime;

    public static IntegranteDto from(Integrante integrante) {
        return builder()
                .nome(integrante.getNome())
                .composicaoTime(integrante.getComposicaoTimes().stream()
                        .map(ComposicaoTimeDto::from)
                        .collect(Collectors.toList()))
                .build();
    }
}
