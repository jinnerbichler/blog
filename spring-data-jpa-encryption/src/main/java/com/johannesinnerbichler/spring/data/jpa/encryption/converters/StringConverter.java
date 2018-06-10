package com.johannesinnerbichler.spring.data.jpa.encryption.converters;

import javax.persistence.Converter;

@Converter
public class StringConverter extends AbstractConverter<String> {

    @Override
    String stringToEntityAttribute(String dbData) {
        return dbData;
    }

    @Override
    String entityAttributeToString(String attribute) {
        return attribute;
    }
}
