package com.wisehero.javaboard.example;

public record ExampleRequest() {

    public record Create(String name, String description) {

    }
}
