package com.eli.springfinalexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringFinalExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringFinalExampleApplication.class, args);
        System.out.println("Inversion Of Control Initialized: Spring framework took control of program's flow.");
    }

}
