package com.example.cicdjenkinscode;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class CiCdJenkinsCodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CiCdJenkinsCodeApplication.class, args);
    }

}
