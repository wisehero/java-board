package com.wisehero.javaboard.example;

import com.wisehero.javaboard.support.error.CoreException;
import com.wisehero.javaboard.support.error.ErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ExampleService {

    private final ExampleRepository exampleRepository;

    public ExampleEntity getExampleEntity(Long id) {
        return exampleRepository.findById(id)
                .orElseThrow(() -> new CoreException(ErrorType.NOT_FOUND, "ExampleEntity not found with id: " + id));
    }

    @Transactional
    public ExampleResponse.ExampleInfo createExampleEntity(String name, String description) {
        ExampleEntity exampleEntity = ExampleEntity.create(name, description);
        ExampleEntity savedExample = exampleRepository.save(exampleEntity);

        return ExampleResponse.ExampleInfo.from(exampleEntity);
    }
}
