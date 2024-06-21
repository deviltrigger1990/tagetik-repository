package it.tagetik.apps.model.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {

        try {
            String dateAsString = p.getText();

            LocalDate ld = LocalDate.parse(dateAsString, formatter);
            return LocalDateTime.of(ld, LocalDateTime.now().toLocalTime());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }


    }


}