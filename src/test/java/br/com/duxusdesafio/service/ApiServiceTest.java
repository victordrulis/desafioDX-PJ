package br.com.duxusdesafio.service;

import br.com.duxusdesafio.business.exception.BusinessException;
import br.com.duxusdesafio.business.model.ComposicaoTime;
import br.com.duxusdesafio.business.model.Integrante;
import br.com.duxusdesafio.business.model.Time;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class ApiServiceTest {

    private static final LocalDate DATA_HOJE = LocalDate.now();
    private static final LocalDate DATA_NOVEMBRO_2022 = LocalDate.of(2022, Month.NOVEMBER, 1);

    @Autowired
    private ApiService apiService;

    @Test
    public void timeDaData() {
        Time time1 = Time.builder().descricao("meu time 1").data(DATA_HOJE).build();
        Time time2 = Time.builder().descricao("meu time 2").data(DATA_NOVEMBRO_2022).build();
        Time time3 = Time.builder().descricao("meu time 3").data(DATA_HOJE).build();

        Time timeDaData = apiService.timeDaData(DATA_NOVEMBRO_2022, Lists.newArrayList(time1, time2, time3));

        Assert.assertNotNull(timeDaData);
        Assert.assertEquals(DATA_NOVEMBRO_2022, timeDaData.getData());
        Assert.assertEquals("meu time 2", timeDaData.getDescricao());
    }

    @Test
    public void timeDaDataNaoPossuiTimeNaData() {
        Time time1 = Time.builder().descricao("meu time 1").data(DATA_HOJE).build();
        Time time3 = Time.builder().descricao("meu time 3").data(DATA_HOJE).build();

        Time timeDaData = apiService.timeDaData(DATA_NOVEMBRO_2022, Lists.newArrayList(time1, time3));

        Assert.assertNull(timeDaData);
    }

    @Test
    public void timeDaDataLancaExcecaoSemDados() {
        LocalDate DATA_NOVEMBRO_2022 = LocalDate.of(2022, Month.NOVEMBER, 1);

        Throwable exception = assertThrows(
                BusinessException.class,
                () -> apiService.timeDaData(DATA_NOVEMBRO_2022, Lists.newArrayList()));

        assertEquals("Argumentos inválidos na busca de time na data.", exception.getMessage());
        assertEquals("A lista esta vazia.", exception.getCause().getMessage());
    }

    @Test
    public void integranteMaisUsado() {
        Time time1 = Time.builder().descricao("meu time 1").data(DATA_HOJE).build();
        Time time2 = Time.builder().descricao("meu time 2").data(DATA_NOVEMBRO_2022).build();
        Time time3 = Time.builder().descricao("meu time 3").data(DATA_HOJE).build();

        Integrante integrante1 = Integrante.builder().id(1L).nome("integrante 1").build();
        Integrante integrante2 = Integrante.builder().id(2L).nome("integrante 2").build();

        ComposicaoTime composicaoTime1 = ComposicaoTime.builder().time(time1).integrante(integrante1).build();
        ComposicaoTime composicaoTime2 = ComposicaoTime.builder().time(time2).integrante(integrante2).build();
        ComposicaoTime composicaoTime3 = ComposicaoTime.builder().time(time3).integrante(integrante1).build();

        time1.setComposicaoTimes(Sets.newHashSet(composicaoTime1, composicaoTime3));
        time2.setComposicaoTimes(Sets.newHashSet(composicaoTime2));

        Integrante integranteMaisUsado = apiService.integranteMaisUsado(DATA_NOVEMBRO_2022, DATA_HOJE, Lists.newArrayList(time1, time2, time3));

        Assert.assertNotNull(integranteMaisUsado);
        Assert.assertEquals(integrante1, integranteMaisUsado);
    }

    @Test
    public void integranteMaisUsadoNaoLancaErrosComListaDeTimesVazia() {
        assertDoesNotThrow(() -> {
            apiService.integranteMaisUsado(DATA_NOVEMBRO_2022, DATA_HOJE, Lists.newArrayList());
        });
    }

    @Test
    public void integrantesDoTimeMaisComum() {
    }

    @Test
    public void funcaoMaisComum() {
    }

    @Test
    public void franquiaMaisFamosa() {
    }

    @Test
    public void contagemPorFranquia() {
    }

    @Test
    public void contagemPorFuncao() {
    }
}