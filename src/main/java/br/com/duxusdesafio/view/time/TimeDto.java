package br.com.duxusdesafio.view.time;

import br.com.duxusdesafio.business.model.Time;
import br.com.duxusdesafio.view.integrante.IntegranteDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimeDto implements Serializable {

    private Long id;
    private String descricao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate data;
    @Singular
    private List<IntegranteDto> integrantes;

    public static TimeDto from(Time time) {
        TimeDtoBuilder timeDtoBuilder = builder()
                .id(time.getId())
                .descricao(time.getDescricao())
                .data(time.getData());

        time.getComposicaoTimes()
                .forEach(composicao -> timeDtoBuilder.integrante(IntegranteDto.from(composicao.getIntegrante())));

        return timeDtoBuilder.build();
    }
}
