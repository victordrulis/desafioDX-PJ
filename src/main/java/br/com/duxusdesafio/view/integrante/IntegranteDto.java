package br.com.duxusdesafio.view.integrante;

import br.com.duxusdesafio.business.model.Integrante;
import br.com.duxusdesafio.view.franquia.FranquiaDto;
import br.com.duxusdesafio.view.funcao.FuncaoDto;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Data
@Builder
public class IntegranteDto implements Serializable {
    private Long id;
    private String nome;
    private FranquiaDto franquia;
    private FuncaoDto funcao;
    @Singular
    private List<IntegranteTimeDto> times;

    public static IntegranteDto from(Integrante integrante) {
        IntegranteDtoBuilder builder = builder()
                .id(integrante.getId())
                .nome(integrante.getNome())
                .franquia(FranquiaDto.from(integrante.getFranquia()))
                .funcao(FuncaoDto.from(integrante.getFuncao()));

        integrante.getComposicaoTimes().stream()
                .filter(Objects::nonNull)
                .forEach(composicaoTime -> builder.time(IntegranteTimeDto.from(composicaoTime.getTime())));

        return builder.build();
    }
}
