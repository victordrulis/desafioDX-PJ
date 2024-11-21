package br.com.duxusdesafio.utils;

import br.com.duxusdesafio.business.exception.BusinessException;

import java.time.LocalDate;

/**
 * Classe de auxilio para datas
 */
public abstract class DateUtils {

    public static final String DATA_INFORMADA_NULA = "A data informada é nula.";
    public static final String DATA_INFORMADA_APOS_DATA_ATUAL = "A data informada é após a data atual.";
    public static final String DATA_INICIAL_POSTERIOR_DATA_FINAL = "A data inicial não pode ser posterior a data final.";

    public static void validarDataNaoNula(LocalDate data) throws BusinessException {
        if(data == null) {
            throw new BusinessException(DATA_INFORMADA_NULA);
        }
    }

    public static void validarDataInicialAposDataFinal(LocalDate dataInicial, LocalDate dataFinal) throws BusinessException {
        if(dataInicial.isAfter(dataFinal)) {
            throw new BusinessException(DATA_INICIAL_POSTERIOR_DATA_FINAL);
        }
    }

    public static void validarDataAposDataAtual(LocalDate data, LocalDate dataAtual) throws BusinessException {
        validarDataNaoNula(data);
        validarDataNaoNula(dataAtual);

        if(data.isAfter(dataAtual)) {
            throw new BusinessException(DATA_INFORMADA_APOS_DATA_ATUAL);
        }
    }

    public static boolean isDataNoPeriodo(LocalDate data, LocalDate dataInicial, LocalDate dataFinal) {
        return (data != null) && (data.isEqual(dataInicial) || data.isAfter(dataInicial)) && (data.isEqual(dataFinal) || data.isBefore(dataFinal));
    }
}
