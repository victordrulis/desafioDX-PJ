package br.com.duxusdesafio.service;

import br.com.duxusdesafio.business.exception.BusinessException;
import br.com.duxusdesafio.business.model.*;
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
import java.util.*;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class ApiServiceTest {

    private static final LocalDate DATA_HOJE = LocalDate.now();
    private static final LocalDate DATA_NOVEMBRO_2022 = LocalDate.of(2022, Month.NOVEMBER, 1);

    @Autowired
    private ApiService apiService;

    private final LocalDate dataTime = LocalDate.of(2024, 3, 1);
    private final LocalDate dataInicial = LocalDate.of(2024, 1, 1);
    private final LocalDate dataFinal = LocalDate.of(2024, 12, 31);

    @Test
    public void timeDaData() {
        Times dadosOsTimes = getTimes();
        Time timeDaData = apiService.timeDaData(DATA_NOVEMBRO_2022, Lists.newArrayList(dadosOsTimes.time1, dadosOsTimes.time2, dadosOsTimes.time3));

        assertNotNull(timeDaData);
        Assert.assertEquals(DATA_NOVEMBRO_2022, timeDaData.getData());
        Assert.assertEquals("meu time 2", timeDaData.getDescricao());
    }

    @Test
    public void timeDaDataNaoPossuiTimeNaData() {
        ArrayList<Time> todosOsTimes = dadosTodosOsTimes(2);

        Time timeDaData = apiService.timeDaData(DATA_NOVEMBRO_2022, todosOsTimes);

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
        Times dadosOsTimes = getTimes();
        Integrantes dadosOsIntegrantes = getIntegrantes();
        ComposicoesTime dadasAsComposicoes = getComposicoesTime(dadosOsTimes, dadosOsIntegrantes);

        dadosOsTimes.time1.setComposicaoTimes(Sets.newHashSet(dadasAsComposicoes.composicaoTime1, dadasAsComposicoes.composicaoTime3));
        dadosOsTimes.time2.setComposicaoTimes(Sets.newHashSet(dadasAsComposicoes.composicaoTime2));

        Integrante integranteMaisUsado = apiService.integranteMaisUsado(DATA_NOVEMBRO_2022, DATA_HOJE, Lists.newArrayList(dadosOsTimes.time1, dadosOsTimes.time2, dadosOsTimes.time3));

        assertNotNull(integranteMaisUsado);
        Assert.assertEquals(dadosOsIntegrantes.integrante1, integranteMaisUsado);
    }

    @Test
    public void integranteMaisUsadoNaoLancaErrosComListaDeTimesVazia() {
        assertDoesNotThrow(() -> {
            apiService.integranteMaisUsado(DATA_NOVEMBRO_2022, DATA_HOJE, Lists.newArrayList());
        });
    }

    @Test
    public void integrantesDoTimeMaisComumNoPeriodo() {
        Composioes dadasAsComposicoes = getComposioes();
        TimesSemArgumentos dadosOsTimes = getTimes(dadasAsComposicoes);

        List<Time> times = Arrays.asList(dadosOsTimes.time1, dadosOsTimes.time2, dadosOsTimes.time3);

        Optional<List<String>> resultado = Optional.ofNullable(apiService.integrantesDoTimeMaisComum(dataInicial, dataFinal, times));

        verificarAComposicaoDeTimesMaisComum(resultado, 2);
    }

    @Test
    public void integrantesDoTimeMaisComumNoPeriodoEmpatado() {
        // Criação de dados de exemplo
        Time time1 = mock(Time.class);
        Time time2 = mock(Time.class);
        Time time3 = mock(Time.class);

        // Mock do comportamento de composicaoTimes
        ComposicaoTime composicaoTime1 = criarComposicao(1L, "Faker");
        ComposicaoTime composicaoTime2 = criarComposicao(2L, "Fallen");
        ComposicaoTime composicaoTime3 = criarComposicao(3L, "Victor");

        when(time1.getComposicaoTimes()).thenReturn(Sets.newHashSet(composicaoTime1, composicaoTime2));
        when(time2.getComposicaoTimes()).thenReturn(Sets.newHashSet(composicaoTime3, composicaoTime1));
        when(time3.getComposicaoTimes()).thenReturn(Sets.newHashSet(composicaoTime1));

        // Mock do método getNome do integrante
        when(time1.getData()).thenReturn(LocalDate.of(2024, 6, 1));
        when(time2.getData()).thenReturn(LocalDate.of(2024, 6, 1));
        when(time3.getData()).thenReturn(LocalDate.of(2024, 6, 1));

        // Lista de times
        List<Time> times = Arrays.asList(time1, time2, time3);

        // Chamada ao método
        Optional<List<String>> resultado = Optional.ofNullable(apiService.integrantesDoTimeMaisComum(dataInicial, dataFinal, times));

        verificarAComposicaoDeTimesMaisComum(resultado, 2);
    }

    @Test
    public void funcaoMaisComum() {
        Times dadosOsTimes = getTimes();
        Integrantes dadosOsIntegrantes = getIntegrantes();
        ComposicoesTime dadasAsComposicoes = getComposicoesTime(dadosOsTimes, dadosOsIntegrantes);

        dadosOsTimes.time1.setComposicaoTimes(Sets.newHashSet(dadasAsComposicoes.composicaoTime1, dadasAsComposicoes.composicaoTime3));
        dadosOsTimes.time2.setComposicaoTimes(Sets.newHashSet(dadasAsComposicoes.composicaoTime2));

        List<Time> todosOsTimes = Arrays.asList(dadosOsTimes.time1, dadosOsTimes.time2, dadosOsTimes.time3);

        String funcaoMaisComum = apiService.funcaoMaisComum(dataInicial, dataFinal, todosOsTimes);

        assertNotNull(funcaoMaisComum);
        assertEquals("Off-lane", funcaoMaisComum);
    }

    @Test
    public void franquiaMaisFamosa() {
        Times dadosOsTimes = getTimes();
        Integrantes dadosOsIntegrantes = getIntegrantes();
        ComposicoesTime dadasAsComposicoes = getComposicoesTime(dadosOsTimes, dadosOsIntegrantes);

        dadosOsTimes.time1.setComposicaoTimes(Sets.newHashSet(dadasAsComposicoes.composicaoTime1, dadasAsComposicoes.composicaoTime3));
        dadosOsTimes.time2.setComposicaoTimes(Sets.newHashSet(dadasAsComposicoes.composicaoTime2));

        List<Time> todosOsTimes = Arrays.asList(dadosOsTimes.time1, dadosOsTimes.time2, dadosOsTimes.time3);

        String franquiaMaisFamosa = apiService.franquiaMaisFamosa(dataInicial, dataFinal, todosOsTimes);

        assertNotNull(franquiaMaisFamosa);
        assertEquals("Frank Aguiar", franquiaMaisFamosa);
    }

    @Test
    public void contagemPorFranquia() {
        Times dadosOsTimes = getTimes();
        Integrantes dadosOsIntegrantes = getIntegrantes();
        ComposicoesTime dadasAsComposicoes = getComposicoesTime(dadosOsTimes, dadosOsIntegrantes);

        dadosOsTimes.time1.setComposicaoTimes(Sets.newHashSet(dadasAsComposicoes.composicaoTime1, dadasAsComposicoes.composicaoTime3));
        dadosOsTimes.time2.setComposicaoTimes(Sets.newHashSet(dadasAsComposicoes.composicaoTime2));

        List<Time> todosOsTimes = Arrays.asList(dadosOsTimes.time1, dadosOsTimes.time2, dadosOsTimes.time3);

        Map<String, Long> contagemPorFranquia = apiService.contagemPorFranquia(dataInicial, dataFinal, todosOsTimes);

        assertNotNull(contagemPorFranquia);
        assertTrue(contagemPorFranquia.containsKey("Frank Aguiar"));
        assertEquals(2L, contagemPorFranquia.get("Frank Aguiar").longValue());
    }

    @Test
    public void contagemPorFuncao() {
        Times dadosOsTimes = getTimes();
        Integrantes dadosOsIntegrantes = getIntegrantes();
        ComposicoesTime dadasAsComposicoes = getComposicoesTime(dadosOsTimes, dadosOsIntegrantes);

        dadosOsTimes.time1.setComposicaoTimes(Sets.newHashSet(dadasAsComposicoes.composicaoTime1, dadasAsComposicoes.composicaoTime3));
        dadosOsTimes.time2.setComposicaoTimes(Sets.newHashSet(dadasAsComposicoes.composicaoTime2));

        List<Time> todosOsTimes = Arrays.asList(dadosOsTimes.time1, dadosOsTimes.time2, dadosOsTimes.time3);

        Map<String, Long> contagemPorFuncao = apiService.contagemPorFuncao(dataInicial, dataFinal, todosOsTimes);

        assertNotNull(contagemPorFuncao);
        assertTrue(contagemPorFuncao.containsKey("Off-lane"));
        assertEquals(2L, contagemPorFuncao.get("Off-lane").longValue());
    }

    private ComposicoesTime getComposicoesTime(Times dadosOsTimes, Integrantes dadosOsIntegrantes) {
        ComposicaoTime composicaoTime1 = ComposicaoTime.builder().time(dadosOsTimes.time1).integrante(dadosOsIntegrantes.integrante1).build();
        ComposicaoTime composicaoTime2 = ComposicaoTime.builder().time(dadosOsTimes.time2).integrante(dadosOsIntegrantes.integrante2).build();
        ComposicaoTime composicaoTime3 = ComposicaoTime.builder().time(dadosOsTimes.time3).integrante(dadosOsIntegrantes.integrante1).build();

        return new ComposicoesTime(composicaoTime1, composicaoTime2, composicaoTime3);
    }

    private static class ComposicoesTime {
        public final ComposicaoTime composicaoTime1;
        public final ComposicaoTime composicaoTime2;
        public final ComposicaoTime composicaoTime3;

        public ComposicoesTime(ComposicaoTime composicaoTime1, ComposicaoTime composicaoTime2, ComposicaoTime composicaoTime3) {
            this.composicaoTime1 = composicaoTime1;
            this.composicaoTime2 = composicaoTime2;
            this.composicaoTime3 = composicaoTime3;
        }
    }

    private Integrantes getIntegrantes() {
        Funcao funcao1 = Funcao.builder().id(1L).nome("Off-lane").build();
        Funcao funcao2 = Funcao.builder().id(2L).nome("HC").build();

        Franquia franquia1 = Franquia.builder().id(1L).nome("Frank Aguiar").build();

        Integrante integrante1 = Integrante.builder().id(1L).nome("integrante 1").funcao(funcao1).franquia(franquia1).build();
        Integrante integrante2 = Integrante.builder().id(2L).nome("integrante 2").funcao(funcao2).franquia(franquia1).build();

        return new Integrantes(integrante1, integrante2);
    }

    private static class Integrantes {
        public final Integrante integrante1;
        public final Integrante integrante2;

        public Integrantes(Integrante integrante1, Integrante integrante2) {
            this.integrante1 = integrante1;
            this.integrante2 = integrante2;
        }
    }

    private Times getTimes() {
        Time time1 = Time.builder().descricao("meu time 1").data(DATA_HOJE).build();
        Time time2 = Time.builder().descricao("meu time 2").data(DATA_NOVEMBRO_2022).build();
        Time time3 = Time.builder().descricao("meu time 3").data(DATA_HOJE).build();

        return new Times(time1, time2, time3);
    }

    private static class Times {
        public final Time time1;
        public final Time time2;
        public final Time time3;

        public Times(Time time1, Time time2, Time time3) {
            this.time1 = time1;
            this.time2 = time2;
            this.time3 = time3;
        }
    }

    private TimesSemArgumentos getTimes(Composioes dadasAsComposicoes) {
        Time time1 = mock(Time.class);
        Time time2 = mock(Time.class);
        Time time3 = mock(Time.class);

        when(time1.getComposicaoTimes()).thenReturn(Sets.newHashSet(dadasAsComposicoes.composicaoTime1, dadasAsComposicoes.composicaoTime2));
        when(time2.getComposicaoTimes()).thenReturn(Sets.newHashSet(dadasAsComposicoes.composicaoTime3, dadasAsComposicoes.composicaoTime1));
        when(time3.getComposicaoTimes()).thenReturn(Sets.newHashSet(dadasAsComposicoes.composicaoTime1, dadasAsComposicoes.composicaoTime2));

        when(time1.getData()).thenReturn(LocalDate.of(2024, 6, 1));
        when(time2.getData()).thenReturn(LocalDate.of(2024, 6, 1));
        when(time3.getData()).thenReturn(LocalDate.of(2024, 6, 1));

        return new TimesSemArgumentos(time1, time2, time3);
    }

    private static class TimesSemArgumentos {
        public final Time time1;
        public final Time time2;
        public final Time time3;

        public TimesSemArgumentos(Time time1, Time time2, Time time3) {
            this.time1 = time1;
            this.time2 = time2;
            this.time3 = time3;
        }
    }

    private Composioes getComposioes() {
        ComposicaoTime composicaoTime1 = criarComposicao(1L, "Faker");
        ComposicaoTime composicaoTime2 = criarComposicao(2L, "Fallen");
        ComposicaoTime composicaoTime3 = criarComposicao(3L, "Victor");

        return new Composioes(composicaoTime1, composicaoTime2, composicaoTime3);
    }

    private static class Composioes {
        public final ComposicaoTime composicaoTime1;
        public final ComposicaoTime composicaoTime2;
        public final ComposicaoTime composicaoTime3;

        public Composioes(ComposicaoTime composicaoTime1, ComposicaoTime composicaoTime2, ComposicaoTime composicaoTime3) {
            this.composicaoTime1 = composicaoTime1;
            this.composicaoTime2 = composicaoTime2;
            this.composicaoTime3 = composicaoTime3;
        }
    }

    private void verificarAComposicaoDeTimesMaisComum(Optional<List<String>> resultado, int qtd) {
        assertTrue(resultado.isPresent());
        assertEquals(qtd, resultado.get().size());
        assertTrue(resultado.get().contains("Faker"));
        assertTrue(resultado.get().contains("Fallen"));
    }

    private ComposicaoTime criarComposicao(Long id, String nome) {
        ComposicaoTime composicao = mock(ComposicaoTime.class);
        Integrante integrante = mock(Integrante.class);
        when(integrante.getId()).thenReturn(id);
        when(integrante.getNome()).thenReturn(nome);
        when(composicao.getIntegrante()).thenReturn(integrante);
        return composicao;
    }

    private static ArrayList<Time> dadosTodosOsTimes(int qtd) {
        ArrayList<Time> todosOsTimes = Lists.newArrayList();
        for(int i = 0; i < qtd; i++) {
            todosOsTimes.add(Time.builder().descricao("meu time " + i).data(DATA_HOJE.minusDays(i)).build());
        }

        return todosOsTimes;
    }
}