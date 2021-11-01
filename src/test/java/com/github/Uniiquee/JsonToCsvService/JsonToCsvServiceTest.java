package com.github.Uniiquee.JsonToCsvService;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import jakarta.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
class JsonToCsvServiceTest {

    @Inject
    EmbeddedApplication<?> application;

    @Test
    void testItWorks() {
        Assertions.assertTrue(application.isRunning());
    }

    @Inject
    JsonToCsvService jsonToCsvService;

    @Test
    void validateJsonSimpleString(){
        try {
            assertEquals("\"Test Item\",99\n" +
                    "\"Test Item 2\",4",jsonToCsvService.convertJsonToCsv("[ {\n" +
                    "  \"name\" : \"Test Item\",\n" +
                    "  \"price\" : 99\n" +
                    "}, {\n" +
                    "  \"name\" : \"Test Item 2\",\n" +
                    "  \"price\" : 4\n" +
                    "} ]"));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
