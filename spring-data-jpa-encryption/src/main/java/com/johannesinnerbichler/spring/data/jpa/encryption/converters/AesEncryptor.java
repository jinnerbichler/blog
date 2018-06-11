package com.johannesinnerbichler.spring.data.jpa.encryption.converters;

import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.codec.Utf8;
import org.springframework.security.crypto.encrypt.BouncyCastleAesCbcBytesEncryptor;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;

import java.util.Random;


import static com.johannesinnerbichler.spring.data.jpa.encryption.converters.DatabaseEncryptionPasswordProperty.DATABASE_ENCRYPTION_PASSWORD;
import static com.johannesinnerbichler.spring.data.jpa.encryption.converters.DatabaseEncryptionPasswordProperty.DATABASE_ENCRYPTION_SALT;

final class AesEncryptor implements TextEncryptor {

    private final BytesEncryptor encryptor;

    AesEncryptor() {
        this.encryptor = new BouncyCastleAesCbcBytesEncryptor(
                DATABASE_ENCRYPTION_PASSWORD,
                DATABASE_ENCRYPTION_SALT,
                new PredictableRandomBytesKeyGenerator(16));
    }

    public String encrypt(String text) {
        return new String(Hex.encode(encryptor.encrypt(Utf8.encode(text))));
    }

    public String decrypt(String encryptedText) {
        return Utf8.decode(encryptor.decrypt(Hex.decode(encryptedText)));
    }

    /**
     * A BytesKeyGenerator that always generates the same sequence of values
     */
    private static class PredictableRandomBytesKeyGenerator implements BytesKeyGenerator {

        private final Random random;

        private final int keyLength;

        PredictableRandomBytesKeyGenerator(int keyLength) {
            this.random = new Random(1);  // always use the same seed
            this.keyLength = keyLength;
        }

        public int getKeyLength() {
            return keyLength;
        }

        public byte[] generateKey() {
            byte[] bytes = new byte[keyLength];
            random.nextBytes(bytes);
            return bytes;
        }
    }
}