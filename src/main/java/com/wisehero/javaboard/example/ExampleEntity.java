package com.wisehero.javaboard.example;

import com.wisehero.javaboard.config.db.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "example")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ExampleEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    public static ExampleEntity create(String name, String description) {
        ExampleEntity example = new ExampleEntity();
        example.name = name;
        example.description = description;
        return example;
    }
}
