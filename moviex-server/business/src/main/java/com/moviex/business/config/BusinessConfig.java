package com.moviex.business.config;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moviex.business.service.impl.DefaultMovieSearchService;
import com.moviex.persistence.entity.movie.MovieSearchMetadata;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Configuration
@EnableAsync
public class BusinessConfig {

    @Bean
    public RestOperations restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(0, customMessageConverter());
        return restTemplate;
    }

    @Bean
    public MappingJackson2HttpMessageConverter customMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(customObjectMapper());
        return converter;
    }

    @Bean
    public ObjectMapper customObjectMapper() {
        return new ObjectMapper() {
            @Override
            public <T> T readValue(InputStream src, JavaType valueType) throws IOException, JsonParseException, JsonMappingException {
                T resultValue = super.readValue(src, valueType);
                if (resultValue.getClass().equals(DefaultMovieSearchService.RequestResultHolder.class)) {
                    DefaultMovieSearchService.RequestResultHolder resultHolder = (DefaultMovieSearchService.RequestResultHolder) resultValue;
                    List<MovieSearchMetadata> movieSearchMetadataList = resultHolder.getMovieSearchMetadataList();

                    String[] publishedImages = restTemplate().postForObject(
                            "http://localhost:8082/img-service/publish",
                            movieSearchMetadataList.stream()
                                    .map(MovieSearchMetadata::getPoster)
                                    .collect(Collectors.toList()),
                            String[].class
                    );
                    IntStream.range(0, publishedImages.length)
                            .forEach(index -> movieSearchMetadataList.get(index).setPoster(publishedImages[index]));
                }
                return resultValue;
            }
        };
    }
}
