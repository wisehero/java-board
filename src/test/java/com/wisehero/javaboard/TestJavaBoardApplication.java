package com.wisehero.javaboard;

import org.springframework.boot.SpringApplication;

public class TestJavaBoardApplication {

    public static void main(String[] args) {
        SpringApplication.from(JavaBoardApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
