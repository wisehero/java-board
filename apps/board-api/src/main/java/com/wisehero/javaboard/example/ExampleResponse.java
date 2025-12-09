package com.wisehero.javaboard.example;

public record ExampleResponse() {

    public record ExampleInfo(
            String name,
            String description
    ){
        static ExampleInfo from (ExampleEntity exampleEntity){
            return new ExampleInfo(exampleEntity.getName(), exampleEntity.getDescription());
        }
    }
}
