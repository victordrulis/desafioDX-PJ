package br.com.duxusdesafio.view.time;

import br.com.duxusdesafio.business.model.ComposicaoTime;
import br.com.duxusdesafio.business.model.Time;
import br.com.duxusdesafio.view.composicaotime.ComposicaoTimeDto;
import br.com.duxusdesafio.view.integrante.IntegranteDto;
import lombok.*;

import java.io.Serializable;
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
public class TimeDto implements Serializable {

    private Long id;
    private String descricao;
    private LocalDate data;
    private List<ComposicaoTimeDto> composicaoTimes;

    public static TimeDto from(Time time) {
        return builder()
                .id(time.getId())
                .descricao(time.getDescricao())
                .data(time.getData())
                .composicaoTimes(Optional.ofNullable(time.getComposicaoTimes()).orElse(new HashSet<>()).stream()
                        .map(ComposicaoTimeDto::from)
                        .collect(Collectors.toList()))
                .build();
    }
}
