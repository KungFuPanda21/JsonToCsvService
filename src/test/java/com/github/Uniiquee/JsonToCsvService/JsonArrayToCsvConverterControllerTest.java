package com.github.Uniiquee.JsonToCsvService;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
public class JsonArrayToCsvConverterControllerTest {

    @Inject
    @Client("/")
    HttpClient httpClient;

    @Test
    void testOk() {
        HttpResponse response = httpClient.toBlocking().exchange(HttpRequest.POST("/convertJsonToCsv", JsonToCsvServiceTest.JSON_ARRAY_TEST_INPUT), String.class);
        assertEquals(HttpResponse.ok().code(),
                response.code());
        assertEquals(JsonToCsvServiceTest.EXPECTED_JSON_ARRAY_AS_CSV_WITHOUT_HEADER,
                response.body());
    }

    @Test()
    void testErrorMessage() {
        HttpClientResponseException exception = Assertions.assertThrows(HttpClientResponseException.class, () -> {
            httpClient.toBlocking().retrieve(HttpRequest.POST("/convertJsonToCsv","INVALID JSON"));
        });
        assertEquals(exception.getResponse().body(), "Unrecognized token 'INVALID': was expecting (JSON String, Number, Array, Object or token 'null', 'true' or 'false')\n" +
                " at [Source: (String)\"INVALID JSON\"; line: 1, column: 8]");
    }
}
