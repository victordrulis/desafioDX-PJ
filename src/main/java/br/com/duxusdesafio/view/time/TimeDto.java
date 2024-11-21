package br.com.duxusdesafio.view.time;

import br.com.duxusdesafio.business.model.ComposicaoTime;
import br.com.duxusdesafio.business.model.Time;
import br.com.duxusdesafio.view.integrante.IntegranteDto;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimeDto {

    private Long id;
    private String descricao;
    private LocalDate data;
    private List<IntegranteDto> integrantes;

    public static TimeDto from(Time time) {
        return builder()
                .id(time.getId())
                .descricao(time.getDescricao())
                .data(time.getData())
                .integrantes(Optional.ofNullable(time.getComposicaoTimes()).orElse(new HashSet<>()).stream()
                        .map(ComposicaoTime::getIntegrante)
                        .map(IntegranteDto::from)
                        .collect(Collectors.toList()))
                .build();
    }
}
