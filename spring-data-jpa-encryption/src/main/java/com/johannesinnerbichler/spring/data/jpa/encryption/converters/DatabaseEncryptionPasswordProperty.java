package com.johannesinnerbichler.spring.data.jpa.encryption.converters;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DatabaseEncryptionPasswordProperty {

    public static String DATABASE_ENCRYPTION_PASSWORD;
    public static String DATABASE_ENCRYPTION_SALT;

    @Value("${innerbichler.database.encryption.password}")
    public void setPassword(String databaseEncryptionPassword) {
        DATABASE_ENCRYPTION_PASSWORD = databaseEncryptionPassword;
    }

    @Value("${innerbichler.database.encryption.salt}")
    public void setSalt(String databaseEncryptionSalt) {
        DATABASE_ENCRYPTION_SALT = databaseEncryptionSalt;
    }
}
