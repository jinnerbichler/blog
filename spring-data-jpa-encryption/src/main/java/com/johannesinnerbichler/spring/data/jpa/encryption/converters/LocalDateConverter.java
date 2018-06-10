package com.johannesinnerbichler.spring.data.jpa.encryption.converters;

import javax.persistence.Converter;
import java.time.LocalDate;

import static java.time.format.DateTimeFormatter.ISO_DATE;

@Converter
public class LocalDateConverter extends AbstractConverter<LocalDate> {
    @Override
    LocalDate stringToEntityAttribute(String data) {
        if (data == null)
            return null;
        return LocalDate.parse(data, ISO_DATE);
    }

    @Override
    String entityAttributeToString(LocalDate attr) {
        if (attr == null)
            return null;
        return attr.format(ISO_DATE);
    }
}
