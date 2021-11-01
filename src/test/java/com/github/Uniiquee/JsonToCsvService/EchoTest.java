package com.github.Uniiquee.JsonToCsvService;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
class EchoTest {

    @Inject
    @Client("/")
    HttpClient httpClient;

    @Test()
    void echoTest() {
        String echo = httpClient.toBlocking().retrieve(HttpRequest.POST("/echo","echoMessage"));
        assertEquals("echoMessage",echo);
    }

}