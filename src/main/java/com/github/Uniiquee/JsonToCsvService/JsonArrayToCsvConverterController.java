package com.github.Uniiquee.JsonToCsvService;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;

@Controller("/convertJsonToCsv")
public class JsonArrayToCsvConverterController {

    @Inject
    JsonToCsvService jsonToCsvService;

    @Post
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public HttpResponse convertJsonToCsv(@Body String jsonArray) {
        try {
            String csv = jsonToCsvService.convertJsonToCsv(jsonArray);
            return HttpResponse.ok(csv);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return HttpResponse.badRequest(e.getMessage());
        }
    }
}
