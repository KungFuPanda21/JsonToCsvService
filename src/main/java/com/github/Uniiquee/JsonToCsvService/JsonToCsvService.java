package com.github.Uniiquee.JsonToCsvService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import jakarta.inject.Singleton;
import jdk.jshell.spi.ExecutionControl;

@Singleton
public class JsonToCsvService {

    private ObjectMapper objectMapper = new ObjectMapper();

    public String convertJsonToCsv(String jsonArrayInput) throws JsonProcessingException {
        return  convertJsonToCsv(jsonArrayInput, false);
    }

    /**
     * Converts a json array to a csv list seperated by ','. The parameter addCsvHeader
     * indicates if the resulting csv will contain a header in the first line.
     * @param jsonArrayInput
     * @param addCsvHeader
     * @return The converted csv
     * @throws JsonProcessingException
     */
    public String convertJsonToCsv(String jsonArrayInput, boolean addCsvHeader) throws JsonProcessingException {
        JsonNode jsonArray = new ObjectMapper().readTree(jsonArrayInput);
        JsonNode firstArrayElement = jsonArray.elements().next();
        CsvSchema schema = getCsvSchemaBasedOnFirstElement(firstArrayElement);
        if(addCsvHeader){
            schema = schema.withHeader();
        }
        CsvMapper csvMapper = new CsvMapper();
        ObjectWriter csvWriter = csvMapper.writerFor(JsonNode.class).with(schema);
        return csvWriter.writeValueAsString(jsonArray).trim();
    }

    private CsvSchema getCsvSchemaBasedOnFirstElement(JsonNode firstArrayElement) {
        CsvSchema.Builder csvSchemaBuilder = CsvSchema.builder();
        firstArrayElement.fieldNames().forEachRemaining(
                fieldName -> {csvSchemaBuilder.addColumn(fieldName);}
        );
        return csvSchemaBuilder.build();
    }

}
