package com.wisehero.javaboard.example;

import com.wisehero.javaboard.support.api.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ExampleController {

    private final ExampleService exampleService;

    @GetMapping("/hello")
    public void hello() {
        log.info("hello");
    }

    @GetMapping("/examples/{id}")
    public ApiResponse<ExampleResponse.ExampleInfo> getExample(@PathVariable Long id) {
        ExampleEntity exampleEntity = exampleService.getExampleEntity(id);
        ExampleResponse.ExampleInfo result = ExampleResponse.ExampleInfo.from(exampleEntity);

        return ApiResponse.success(result);
    }


    @PostMapping("/examples")
    public ApiResponse<ExampleResponse.ExampleInfo> newExample(@RequestBody ExampleRequest.Create request) {
        ExampleResponse.ExampleInfo result = exampleService.createExampleEntity(
                request.name(),
                request.description());

        return ApiResponse.success(result);
    }
}
