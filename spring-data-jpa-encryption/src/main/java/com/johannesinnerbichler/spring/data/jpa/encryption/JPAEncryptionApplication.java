package com.johannesinnerbichler.spring.data.jpa.encryption;

import com.johannesinnerbichler.spring.data.jpa.encryption.user.Customer;
import com.johannesinnerbichler.spring.data.jpa.encryption.user.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class JPAEncryptionApplication {


    public static void main(String[] args) {
        SpringApplication.run(JPAEncryptionApplication.class, args);
    }

    @Component
    public static class DataLoader implements CommandLineRunner {

        private final Logger logger = LoggerFactory.getLogger(DataLoader.class);

        @Autowired
        CustomerRepository customerRepository;

        @Override
        public void run(String... strings) {

            // save different customers (columns are encrypted before stored in database)
            Customer customer1 = new Customer();
            customer1.setFirstName("Johannes");
            customer1.setLastName("Innerbichler");
            customer1.setEmail("j.innerbichler@gmail.com");
            customer1.setBirthDate(LocalDate.of(1987, 6, 17));
            customer1.setCreationDate(LocalDateTime.now());
            customerRepository.save(customer1);
            logger.info("Stored customer one");
            Customer customer2 = new Customer();
            customer2.setFirstName("Jack");
            customer2.setLastName("Black");
            customer2.setEmail("jack@black.example");
            customer2.setBirthDate(LocalDate.of(1984, 8, 17));
            customer2.setCreationDate(LocalDateTime.now());
            customerRepository.save(customer2);
            logger.info("Stored customer two");

            // find customers by mail (columns are decrypted after fetched from database)
            assert customerRepository.findOneByEmail("j.innerbichler@gmail.com") != null;
            logger.info("Found customer one");
            assert customerRepository.findOneByEmail("jack@black.example") != null;
            logger.info("Found customer two");
        }
    }
}
