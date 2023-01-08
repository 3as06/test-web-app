package main;

import main.model.service.impl.CustomerImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"main.model.entity"})
public class Main {
    CustomerImpl customer;
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);


    }
}