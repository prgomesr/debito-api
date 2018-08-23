package br.prgomesr.debitoapi;

import br.prgomesr.debitoapi.config.property.DebitoApiProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(DebitoApiProperty.class)
public class DebitoApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DebitoApiApplication.class, args);
    }
}
