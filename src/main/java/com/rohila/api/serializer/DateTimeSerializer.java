package com.rohila.api.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTimeSerializer extends JsonSerializer<LocalDate> {

    /**
     * Overriden method to serialize
     *
     * @param value       - value
     * @param gen         - gen
     * @param serializers - serializers
     * @throws IOException
     */
    @Override
    public void serialize(
            LocalDate value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException {
        gen.writeString(ObjectUtils.isEmpty(value) ? null : DateTimeFormatter.ISO_LOCAL_DATE.format(value));
    }
}