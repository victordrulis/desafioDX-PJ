package br.com.duxusdesafio.view.time;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TimeDto {

    public String descricao;
    public LocalDate data;
}
