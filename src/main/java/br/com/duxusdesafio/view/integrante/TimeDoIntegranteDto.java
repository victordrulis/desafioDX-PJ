package br.com.duxusdesafio.view.integrante;

import br.com.duxusdesafio.business.model.Time;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TimeDoIntegranteDto {
    private Long time_id;
    private String time;

    public static TimeDoIntegranteDto from(Time time) {
        return builder()
                .time_id(time.getId())
                .time(time.getDescricao())
                .build();
    }
}
