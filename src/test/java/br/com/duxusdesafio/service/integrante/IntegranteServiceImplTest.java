package br.com.duxusdesafio.service.integrante;

import br.com.duxusdesafio.business.model.*;
import com.google.common.collect.Sets;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class IntegranteServiceImplTest {

    @InjectMocks
    private IntegranteServiceImpl integranteService;

    @Mock
    private Time time1, time2;

    @Mock
    private ComposicaoTime composicaoTime1, composicaoTime2;

    @Mock
    private Integrante integrante1, integrante2;

    private final LocalDate dataTime = LocalDate.of(2024, 2, 1);
    private final LocalDate dataInicial = LocalDate.of(2024, 1, 1);
    private final LocalDate dataFinal = LocalDate.of(2024, 4, 1);

    @Test
    public void salvar() {
    }

    @Test
    public void obterTodos() {
    }

    @Test
    public void obter() {
    }

    @Test
    public void excluir() {
    }

    @Test
    public void obterIntegrantesDoTimeMaisComumNoPeriodo() {
    }


    @Test
    public void testObterComposicaoTimeMaisComum() {
        dadoOsTimes times = getDadoOsTimes();
        dadasAsComposioes(times);

        List<String> resultado = integranteService.obterComposicaoTimeMaisComum(times.times, dataInicial, dataFinal);

        verificarAComposicaoDeTimesMaisComum(resultado);
    }

    private void verificarAComposicaoDeTimesMaisComum(List<String> resultado) {
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertTrue(resultado.contains("Faker"));
        assertTrue(resultado.contains("Fallen"));
    }

    private void dadasAsComposioes(dadoOsTimes times) {
        Funcao funcaoMock = mock(Funcao.class);
        Franquia franquiaMock = mock(Franquia.class);

        Integrante integrante1 = Integrante.builder()
                .id(1L)
                .nome("Faker")
                .composicaoTime(composicaoTime1)
                .composicaoTime(composicaoTime2)
                .funcao(funcaoMock)
                .franquia(franquiaMock)
                .build();

        Integrante integrante2 = Integrante.builder()
                .id(2L)
                .nome("Fallen")
                .composicaoTime(composicaoTime1)
                .composicaoTime(composicaoTime2)
                .funcao(funcaoMock)
                .franquia(franquiaMock)
                .build();

        when(composicaoTime1.getIntegrante()).thenReturn(integrante1);
        when(composicaoTime1.getTime()).thenReturn(times.time1);
        when(composicaoTime2.getIntegrante()).thenReturn(integrante2);
        when(composicaoTime2.getTime()).thenReturn(times.time2);
    }

    private dadoOsTimes getDadoOsTimes() {
        Time time1 = Time.builder()
                .id(1L)
                .data(dataTime)
                .descricao("Time 1")
                .composicaoTime(composicaoTime1)
                .composicaoTime(composicaoTime2)
                .build();

        Time time2 = Time.builder()
                .id(2L)
                .data(dataTime)
                .descricao("Time 2")
                .composicaoTime(composicaoTime1)
                .composicaoTime(composicaoTime2)
                .build();

        List<Time> times = Arrays.asList(time1, time2);
        return new dadoOsTimes(time1, time2, times);
    }

    private static class dadoOsTimes {
        public final Time time1;
        public final Time time2;
        public final List<Time> times;

        public dadoOsTimes(Time time1, Time time2, List<Time> times) {
            this.time1 = time1;
            this.time2 = time2;
            this.times = times;
        }
    }

    @Test
    public void testGetComposicaoTimeMaisComum() {
        Time timeMock1 = mock(Time.class);
        Time timeMock2 = mock(Time.class);

        // Mockando os integrantes
        Integrante integranteMock1 = mock(Integrante.class);
        Integrante integranteMock2 = mock(Integrante.class);

        // Configurando os mocks
        when(integranteMock1.getId()).thenReturn(1L);
        when(integranteMock2.getId()).thenReturn(2L);

        // Criando Set de integrantes para os times
        Set<Integrante> integrantesMock1 = new TreeSet<>(Comparator.comparingLong(Integrante::getId));
        integrantesMock1.add(integranteMock1);

        Set<Integrante> integrantesMock2 = new TreeSet<>(Comparator.comparingLong(Integrante::getId));
        integrantesMock2.add(integranteMock2);

        // Mockando composicoes
        ComposicaoTime composicaoTimeMock1 = mock(ComposicaoTime.class);
        ComposicaoTime composicaoTimeMock2 = mock(ComposicaoTime.class);

        when(composicaoTimeMock1.getIntegrante()).thenReturn(integranteMock1);
        when(composicaoTimeMock1.getTime()).thenReturn(timeMock1);
        when(composicaoTimeMock2.getIntegrante()).thenReturn(integranteMock1);
        when(composicaoTimeMock2.getTime()).thenReturn(timeMock2);

        // Mockando a lista de times
        when(timeMock1.getComposicaoTimes()).thenReturn(Sets.newHashSet(composicaoTimeMock1));
        when(timeMock1.getData()).thenReturn(dataTime);
        when(timeMock2.getComposicaoTimes()).thenReturn(Sets.newHashSet(composicaoTimeMock2));
        when(timeMock2.getData()).thenReturn(dataTime);

        // Retorno esperado do método
        Map<Set<Integrante>, Long> composicoesRepetidasMock = new HashMap<>();
        composicoesRepetidasMock.put(integrantesMock1, 2L); // mock de repetição de 2 vezes

        List<Time> times = Arrays.asList(timeMock1, timeMock2);

        // Verificando se o método do serviço retorna o Set mais comum
        Optional<Set<Integrante>> resultado = integranteService.getComposicaoTimeMaisComum(times, dataInicial, dataFinal);

        assertTrue(resultado.isPresent());
        assertEquals(integrantesMock1, resultado.get());
    }


    @Test
    public void obterIntegranteComMaiorOcorrencia() {
    }
}