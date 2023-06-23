package br.com.trajy.graphql.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ModelMapperBean {

    @Bean
    @Scope(scopeName = "singleton")
    public ModelMapper instanceModelMapper() {
        return new ModelMapper();
    }
}
