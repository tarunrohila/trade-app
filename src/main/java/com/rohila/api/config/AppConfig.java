package com.rohila.api.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.rohila.api.exception.CoreExceptionHandler;
import com.rohila.api.serializer.DataFilter;
import com.rohila.api.serializer.DateTimeSerializer;
import com.rohila.api.service.TradeService;
import com.rohila.api.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static com.sun.javafx.fxml.expression.Expression.or;

/**
 * Class which is used to provide configuration beans
 *
 * @author Tarun Rohila
 */
@Configuration
@EnableSwagger2
public class AppConfig {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private TradeService tradeService;

    /**
     * Method to create bean for exception handler
     *
     * @return default core exception handler
     */
    @Bean
    public CoreExceptionHandler getCoreExceptionHandler() {
        return new CoreExceptionHandler();
    }


    /**
     * Method to customize object mapper with configuration
     *
     * @return objectMapper
     */
    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = JsonUtils.getObjectMapper();
        mapper.setFilterProvider(new SimpleFilterProvider().addFilter("DataFilter", new DataFilter()));
        // To enable controller advice processing of null when return type is object
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        // To exlude from response fields with null or not populated value
        mapper.setSerializationInclusion(JsonInclude.Include.NON_ABSENT);
        return mapper;
    }

    /**
     * Method to configure swagger
     * @return api swagger
     */
    @Bean
    public Docket swaggerConfig() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfo());
    }

    /**
     * MEthod to add api info for swagger documentation
     * @return api info
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Trade APP")
                .description("Trade APP")
                .contact(new Contact("Tarun", "http://localhost:8080", "tarunrohila@gmail.com")).version("1.0").build();
    }

    @Scheduled(cron = "${trade.expiry.scheduler.cron}")
    public void reportCurrentTime() {
        tradeService.updateExpiryFlags();
    }

}
