package br.com.duxusdesafio.utils;

import br.com.duxusdesafio.business.exception.BusinessException;

import java.time.LocalDate;

/**
 * Classe de auxilio para datas
 */
public abstract class DateUtils {

    public static final String DATA_INFORMADA_NULA = "A data informada é nula.";
    public static final String DATA_INFORMADA_APOS_DATA_ATUAL = "A data informada é após a data atual.";

    public static void validarDataNaoNula(LocalDate data) throws BusinessException {
        if(data == null) {
            throw new BusinessException(DATA_INFORMADA_NULA);
        }
    }

    public static void validarDataAposDataAtual(LocalDate data, LocalDate dataAtual) throws BusinessException {
        validarDataNaoNula(data);
        validarDataNaoNula(dataAtual);

        if(data.isAfter(dataAtual)) {
            throw new BusinessException(DATA_INFORMADA_APOS_DATA_ATUAL);
        }
    }
}
