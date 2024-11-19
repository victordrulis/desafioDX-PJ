package br.com.duxusdesafio.view.dto;

import lombok.Data;

import java.util.Set;

@Data
public class ComposicaoTimeDto {

    private Set<IntegranteDto> listaIntegrante;
    private TimeDto time;
}
