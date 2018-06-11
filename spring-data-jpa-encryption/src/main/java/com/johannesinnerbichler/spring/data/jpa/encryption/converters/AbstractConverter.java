package com.johannesinnerbichler.spring.data.jpa.encryption.converters;

import org.springframework.security.crypto.encrypt.TextEncryptor;

import javax.persistence.AttributeConverter;

import static com.johannesinnerbichler.spring.data.jpa.encryption.converters.DatabaseEncryptionPasswordProperty.DATABASE_ENCRYPTION_PASSWORD;

abstract class AbstractConverter<T> implements AttributeConverter<T, String> {

    @Override
    public String convertToDatabaseColumn(T attribute) {
        TextEncryptor encryptor = getEncryptor();
        if (encryptor != null && attribute != null)
            return encrypt(encryptor, attribute);
        return entityAttributeToString(attribute);
    }

    @Override
    public T convertToEntityAttribute(String dbData) {
        TextEncryptor encryptor = getEncryptor();
        if (encryptor != null && dbData != null)
            return decrypt(encryptor, dbData);
        return stringToEntityAttribute(dbData);
    }

    private TextEncryptor getEncryptor() {
        return DATABASE_ENCRYPTION_PASSWORD != null ? new AesEncryptor() : null;
    }

    abstract T stringToEntityAttribute(String data);

    abstract String entityAttributeToString(T attribute);

    private String encrypt(TextEncryptor encryptor, T attribute) {
        String attributeString = entityAttributeToString(attribute);
        return encryptor.encrypt(attributeString);
    }

    private T decrypt(TextEncryptor encryptor, String attributeString) {
        String decryptedAttributeString = encryptor.decrypt(attributeString);
        return stringToEntityAttribute(decryptedAttributeString);
    }
}
