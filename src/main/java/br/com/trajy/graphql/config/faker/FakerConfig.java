package br.com.trajy.graphql.config.faker;

import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Locale;

@Configuration
public class FakerConfig {

    @Bean
    public Faker instanceFaker() {
        return Faker.instance(new Locale.Builder().setLanguage("pt").setRegion("BR").build());
    }

}
