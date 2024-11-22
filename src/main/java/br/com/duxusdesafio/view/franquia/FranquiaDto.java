package br.com.duxusdesafio.view.franquia;

import br.com.duxusdesafio.business.model.Franquia;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FranquiaDto {

    private Long id;
    private String nome;

    public static FranquiaDto from(Franquia franquia) {
        return builder()
                .id(franquia.getId())
                .nome(franquia.getNome())
                .build();
    }
}
