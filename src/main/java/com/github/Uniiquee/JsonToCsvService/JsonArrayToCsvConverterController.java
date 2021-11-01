package com.github.Uniiquee.JsonToCsvService;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@Controller("/convertJsonToCsv")
public class JsonArrayToCsvConverterController {

    private static final Logger LOG = LoggerFactory.getLogger(JsonArrayToCsvConverterController.class);

    @Inject
    JsonToCsvService jsonToCsvService;

    @Post
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public HttpResponse convertJsonToCsv(@Body String jsonArray,
                                         @QueryValue("withCsvHeader") Optional<Boolean> withCsvHeader) {
        try {
            LOG.info("Request to convertJsonToCsv with parameter: {} and queryParameter: {}", jsonArray,withCsvHeader);
            String csv;
            if(withCsvHeader.isPresent()){
                csv = jsonToCsvService.convertJsonToCsv(jsonArray,withCsvHeader.get());
            }else {
                csv = jsonToCsvService.convertJsonToCsv(jsonArray,false);
            }
            return HttpResponse.ok(csv);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return HttpResponse.badRequest(e.getMessage());
        }
    }
}
