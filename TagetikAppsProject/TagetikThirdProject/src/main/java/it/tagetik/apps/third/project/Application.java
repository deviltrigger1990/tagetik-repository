package it.tagetik.apps.third.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("it.tagetik.apps")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}