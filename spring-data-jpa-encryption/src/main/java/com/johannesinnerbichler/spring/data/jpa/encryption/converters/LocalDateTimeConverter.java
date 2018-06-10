package com.johannesinnerbichler.spring.data.jpa.encryption.converters;

import javax.persistence.Converter;
import java.time.LocalDateTime;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;

@Converter
public class LocalDateTimeConverter extends AbstractConverter<LocalDateTime> {
    @Override
    LocalDateTime stringToEntityAttribute(String data) {
        if (data == null)
            return null;
        return LocalDateTime.parse(data, ISO_DATE_TIME);
    }

    @Override
    String entityAttributeToString(LocalDateTime attr) {
        if (attr == null)
            return null;
        return attr.format(ISO_DATE_TIME);
    }
}
