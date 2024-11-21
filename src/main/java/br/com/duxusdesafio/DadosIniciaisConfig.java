package br.com.duxusdesafio;

import br.com.duxusdesafio.business.model.ComposicaoTime;
import br.com.duxusdesafio.business.model.Franquia;
import br.com.duxusdesafio.business.model.Integrante;
import br.com.duxusdesafio.business.model.Time;
import br.com.duxusdesafio.business.repository.composicaotime.ComposicaoTimeRepository;
import br.com.duxusdesafio.business.repository.franquia.FranquiaRepository;
import br.com.duxusdesafio.business.repository.integrante.IntegranteRepository;
import br.com.duxusdesafio.business.repository.time.TimeRepository;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;

@Configuration
public class DadosIniciaisConfig extends WebMvcConfigurationSupport {

    private static final Logger logger = LoggerFactory.getLogger(DadosIniciaisConfig.class);

    @Bean
    CommandLineRunner initDatabase(TimeRepository timeRepository, FranquiaRepository franquiaRepository,
                                   IntegranteRepository integranteRepository, ComposicaoTimeRepository composicaoTimeRepository) {
        return args -> {
            LocalDate data = LocalDate.of(2024, 11, 20);

            Franquia franquia = Franquia.builder().nome("Franquia Pre 1").build();
            logger.info(String.format("Pre-carregando %s", franquiaRepository.save(franquia)));

            Time time = timeRepository.save(Time.builder().descricao("time pre-carregado 1").data(data).franquia(franquia).build());
            logger.info(String.format("Pre-carregando %s", time));

            Integrante integrante = integranteRepository.save(Integrante.builder().nome("Integrante pre1").build());
            logger.info(String.format("Pre-carregando %s", integrante));

            ComposicaoTime composicaoTime = ComposicaoTime.builder().integrante(integrante).time(time).data(data).build();
            time.setComposicaoTimes(Sets.newHashSet(composicaoTime));
            timeRepository.save(time);

            logger.info(String.format("Pre-carregando %s", composicaoTime));
        };
    }

    @Bean
    @Override
    public FormattingConversionService mvcConversionService() {
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService(false);

        DateTimeFormatterRegistrar dateTimeRegistrar = new DateTimeFormatterRegistrar();
        dateTimeRegistrar.setDateFormatter(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        dateTimeRegistrar.setDateTimeFormatter(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        dateTimeRegistrar.registerFormatters(conversionService);

        DateFormatterRegistrar dateRegistrar = new DateFormatterRegistrar();
        dateRegistrar.setFormatter(new DateFormatter("dd-MM-yyyy"));
        dateRegistrar.registerFormatters(conversionService);

        return conversionService;
    }
}
