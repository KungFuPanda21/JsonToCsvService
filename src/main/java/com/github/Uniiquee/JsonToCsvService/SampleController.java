package com.github.Uniiquee.JsonToCsvService;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;

@Controller("/echo")
public class SampleController {

    @Post
    @Produces(MediaType.TEXT_PLAIN)
    public String echo(@Body String echoMessage) {
        return echoMessage;
    }
}
