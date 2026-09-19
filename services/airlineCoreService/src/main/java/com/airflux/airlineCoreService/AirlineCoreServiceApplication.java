package com.airflux.airlineCoreService;

import com.airflux.payload.exception.GlobalExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@Import(GlobalExceptionHandler.class)
@EnableJpaAuditing
public class AirlineCoreServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirlineCoreServiceApplication.class, args);
    }

}
