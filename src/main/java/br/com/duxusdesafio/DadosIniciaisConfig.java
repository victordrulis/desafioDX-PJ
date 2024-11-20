package br.com.duxusdesafio;

import br.com.duxusdesafio.business.model.Time;
import br.com.duxusdesafio.business.repository.time.TimeRepository;
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

@Configuration
public class DadosIniciaisConfig extends WebMvcConfigurationSupport {

    private static final Logger logger = LoggerFactory.getLogger(DadosIniciaisConfig.class);

    @Bean
    CommandLineRunner initDatabase(TimeRepository timeRepository) {
        return args -> logger.info(String.format("Pre-carregando %s", timeRepository.save(new Time(1L, "time pre-carregado 1", LocalDate.now(), null, null))));
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
