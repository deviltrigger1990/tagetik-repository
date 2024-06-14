package it.tagetik.apps.second.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "it.tagetik.apps.model.decorator")
@EntityScan("it.tagetik.apps")
@ComponentScan(basePackages = {"it.tagetik.apps", "it.tagetik.apps.model"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}