package com.wisehero.javaboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JavaBoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaBoardApplication.class, args);
    }

}
