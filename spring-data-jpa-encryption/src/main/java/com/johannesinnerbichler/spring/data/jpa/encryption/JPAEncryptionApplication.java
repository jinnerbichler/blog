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
            Customer customer1 = new Customer();
            customer1.setFirstName("Johannes");
            customer1.setLastName("Innerbichler");
            customer1.setEmail("j.innerbichler@gmail.com");
            customerRepository.save(customer1);
            logger.info("Stored customer one");
        }
    }
}
