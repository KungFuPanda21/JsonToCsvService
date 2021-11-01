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

    public static final String JSON_ARRAY_TEST_INPUT = "[ {\n" +
            "  \"name\" : \"Test Item\",\n" +
            "  \"price\" : 99\n" +
            "}, {\n" +
            "  \"name\" : \"Test Item 2\",\n" +
            "  \"price\" : 4\n" +
            "} ]";
    public static final String EXPECTED_JSON_ARRAY_AS_CSV_WITHOUT_HEADER = "\"Test Item\",99\n" +
            "\"Test Item 2\",4";
    @Inject
    EmbeddedApplication<?> application;

    @Test
    void testItWorks() {
        Assertions.assertTrue(application.isRunning());
    }

    @Inject
    JsonToCsvService jsonToCsvService;

    @Test
    void convertJsonToCsv() throws JsonProcessingException {
            assertEquals(EXPECTED_JSON_ARRAY_AS_CSV_WITHOUT_HEADER,jsonToCsvService.convertJsonToCsv(JSON_ARRAY_TEST_INPUT));
    }

    @Test
    void convertJsonToCsvWithHeader() throws JsonProcessingException {
            assertEquals("name,price\n" +
                    "\"Test Item\",99\n" +
                    "\"Test Item 2\",4",jsonToCsvService.convertJsonToCsv(JSON_ARRAY_TEST_INPUT, true));
    }

    @Test
    void convertJsonToCsvWithoutHeader() throws JsonProcessingException {
            assertEquals(EXPECTED_JSON_ARRAY_AS_CSV_WITHOUT_HEADER,jsonToCsvService.convertJsonToCsv(JSON_ARRAY_TEST_INPUT, false));

    }

}
