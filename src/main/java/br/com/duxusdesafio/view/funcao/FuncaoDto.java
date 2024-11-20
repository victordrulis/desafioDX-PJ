package br.com.duxusdesafio.view.funcao;

import br.com.duxusdesafio.business.model.Funcao;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FuncaoDto {

    private Long id;
    private String nome;

    public static FuncaoDto from(Funcao funcao) {
        return builder()
                .nome(funcao.getNome())
                .build();
    }
}
