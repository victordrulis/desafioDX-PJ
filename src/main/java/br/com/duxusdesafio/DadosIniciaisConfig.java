package br.com.duxusdesafio;

import br.com.duxusdesafio.business.model.Time;
import br.com.duxusdesafio.business.repository.time.TimeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DadosIniciaisConfig {

    private static final Logger logger = LoggerFactory.getLogger(DadosIniciaisConfig.class);

    @Bean
    CommandLineRunner initDatabase(TimeRepository timeRepository) {
        return args -> logger.info(String.format("Pre-carregando %s", timeRepository.save(new Time(1L, "time pre-carregado 1", LocalDate.now(), null, null))));
    }


}
