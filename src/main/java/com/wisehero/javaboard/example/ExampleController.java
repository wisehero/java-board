package com.wisehero.javaboard.example;

import com.wisehero.javaboard.support.api.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ExampleController {

    private final ExampleService exampleService;

    @GetMapping("/hello")
    public void hello() {
        log.info("hello");
    }

    @PostMapping("/example")
    public ApiResponse<ExampleResponse.ExampleInfo> newExample(@RequestBody ExampleRequest.Create request) {
        ExampleResponse.ExampleInfo result = exampleService.createExampleEntity(
                request.name(),
                request.description());

        return ApiResponse.success(result);
    }
}
