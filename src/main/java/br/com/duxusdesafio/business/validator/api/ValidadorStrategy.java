package br.com.duxusdesafio.business.validator.api;

import br.com.duxusdesafio.business.exception.TecException;
import br.com.duxusdesafio.business.model.Franquia;
import br.com.duxusdesafio.business.model.Funcao;
import br.com.duxusdesafio.business.validator.franquia.FranquiaAtualizarValidatorImpl;
import br.com.duxusdesafio.business.validator.franquia.FranquiaExcluirValidatorImpl;
import br.com.duxusdesafio.business.validator.franquia.FranquiaSalvarValidatorImpl;
import br.com.duxusdesafio.business.validator.funcao.FuncaoAtualizarValidatorImpl;
import br.com.duxusdesafio.business.validator.funcao.FuncaoExcluirValidatorImpl;
import br.com.duxusdesafio.business.validator.funcao.FuncaoSalvarValidatorImpl;
import br.com.duxusdesafio.utils.AcoesEnum;
import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Strategy para obter os validadores de acordo com a entidade e ações
 */
@Service
public class ValidadorStrategy {

    private static final Map<Class<?>, Map<AcoesEnum, Validador>> validadoresMap = new HashMap<>();

    public ValidadorStrategy(FranquiaAtualizarValidatorImpl franquiaAtualizarValidatorImpl,
                             FranquiaExcluirValidatorImpl franquiaExcluirValidatorImpl,
                             FranquiaSalvarValidatorImpl franquiaSalvarValidatorImpl,
                             FuncaoAtualizarValidatorImpl funcaoAtualizarValidatorImpl,
                             FuncaoExcluirValidatorImpl funcaoExcluirValidatorImpl,
                             FuncaoSalvarValidatorImpl funcaoSalvarValidatorImpl) {
        validadoresMap.put(Franquia.class, ImmutableMap.<AcoesEnum, Validador> builder()
                .put(AcoesEnum.SALVAR, franquiaSalvarValidatorImpl)
                .put(AcoesEnum.EXCLUIR, franquiaExcluirValidatorImpl)
                .put(AcoesEnum.ATUALIZAR, franquiaAtualizarValidatorImpl)
                .build()
        );
        validadoresMap.put(Funcao.class, ImmutableMap.<AcoesEnum, Validador> builder()
                .put(AcoesEnum.SALVAR, funcaoSalvarValidatorImpl)
                .put(AcoesEnum.EXCLUIR, funcaoExcluirValidatorImpl)
                .put(AcoesEnum.ATUALIZAR, funcaoAtualizarValidatorImpl)
                .build()
        );
    }

    /**
     * Obtém os validadores para uma determinada entidade.
     *
     * @param entidade Classe da entidade
     * @return Mapa de ações com seus respectivos validadores
     */
    public Map<AcoesEnum, Validador> getValidadoresPorEntidade(Class<?> entidade) {
        Map<AcoesEnum, Validador> validadores = validadoresMap.get(entidade);
        if (validadores == null) {
            throw new TecException("Nenhum validador encontrado para " + entidade.getSimpleName());
        }
        return validadores;
    }

    /**
     * Obtém a estratégia de validadores por entidade.
     *
     * @param entidade Classe da entidade
     * @return Mapa com ações e seus respectivos validadores
     */
    public Map<AcoesEnum, Validador> getValidadores(Class<?> entidade) {
        return getValidadoresPorEntidade(entidade);
    }
}
