package br.com.duxusdesafio.utils;

import br.com.duxusdesafio.business.exception.BusinessException;

import java.time.LocalDate;

/**
 * Classe de auxilio para datas
 */
public abstract class DateUtils {

    public static void validarDataNaoNula(LocalDate data) throws BusinessException {
        if(data == null) {
            throw new BusinessException("A data informada é nula.");
        };
    }

    public static void validarDataAposDataAtual(LocalDate data, LocalDate dataAtual) throws BusinessException {
        validarDataNaoNula(data);
        validarDataNaoNula(dataAtual);

        if(data.isAfter(dataAtual)) {
            throw new BusinessException("A data informada é após a data atual.");
        }
    }
}
