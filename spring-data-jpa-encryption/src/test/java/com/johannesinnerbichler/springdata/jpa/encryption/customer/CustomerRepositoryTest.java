package com.johannesinnerbichler.springdata.jpa.encryption.customer;

import com.johannesinnerbichler.spring.data.jpa.encryption.JPAEncryptionApplication;
import com.johannesinnerbichler.spring.data.jpa.encryption.converters.DatabaseEncryptionPasswordProperty;
import com.johannesinnerbichler.spring.data.jpa.encryption.user.Customer;
import com.johannesinnerbichler.spring.data.jpa.encryption.user.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static com.johannesinnerbichler.springdata.jpa.encryption.customer.CustomerRepositoryTest.Helper.disableDatabaseEncryption;
import static com.johannesinnerbichler.springdata.jpa.encryption.customer.CustomerRepositoryTest.Helper.enableDatabaseEncryption;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JPAEncryptionApplication.class)
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    private Customer customer;

    @Before
    public void setUp() {
        enableDatabaseEncryption(testEntityManager);
        customer = new Customer();
    }

    @Test
    public void save_should_persist_customer_with_auto_incremented_id() {
        // Given
        Customer firstPersist = customerRepository.save(customer);
        Customer secondCustomer = new Customer();

        // When
        Customer secondPersist = customerRepository.save(secondCustomer);

        // Then
        assertThat(secondPersist.getId()).isEqualTo(firstPersist.getId() + 1);
    }

    @Test
    public void save_should_verify_that_encryption_is_enabled_on_first_name_field() {
        // Given
        String plainFirstName = "plain first name";
        customer.setFirstName(plainFirstName);
        Customer savedCustomerWithEncryptionEnabled = customerRepository.save(customer);
        disableDatabaseEncryption(testEntityManager);

        // When
        Customer customerRetrievedWithoutEncryptionEnabled = testEntityManager.find(Customer.class, savedCustomerWithEncryptionEnabled.getId());

        // Then
        assertThat(customerRetrievedWithoutEncryptionEnabled.getFirstName())
                .isNotEqualTo(plainFirstName)
                .isEqualTo("73d51abbd89cb8196f0efb6892f94d685f30486dde0d029c8d9c2813b4adecd6b58588f5590b5c252b15cff36e7c84ab");
    }

    @Test
    public void save_should_verify_that_encryption_is_enabled_on_last_name_field() {
        // Given
        String plainLastName = "plain last name";
        customer.setLastName(plainLastName);
        Customer savedCustomerWithEncryptionEnabled = customerRepository.save(customer);
        disableDatabaseEncryption(testEntityManager);

        // When
        Customer customerRetrievedWithoutEncryptionEnabled = testEntityManager.find(Customer.class, savedCustomerWithEncryptionEnabled.getId());

        // Then
        assertThat(customerRetrievedWithoutEncryptionEnabled.getLastName())
                .isNotEqualTo(plainLastName)
                .isEqualTo("73d51abbd89cb8196f0efb6892f94d68394e0407650f9089c4e495ccf32448b0");
    }

    @Test
    public void save_should_verify_that_encryption_is_enabled_on_email_field() {
        // Given
        String plainEmail = "email@example.org";
        customer.setEmail(plainEmail);
        Customer savedCustomerWithEncryptionEnabled = customerRepository.save(customer);
        disableDatabaseEncryption(testEntityManager);

        // When
        Customer customerRetrievedWithoutEncryptionEnabled = testEntityManager.find(Customer.class, savedCustomerWithEncryptionEnabled.getId());

        // Then
        assertThat(customerRetrievedWithoutEncryptionEnabled.getEmail())
                .isNotEqualTo(plainEmail)
                .isEqualTo("73d51abbd89cb8196f0efb6892f94d6810fab12e618bebac38c7d01b5761085811cc22207613813a2ef1ff51b7f33399");
    }

    final static class Helper {

        private Helper() {
        }

        static void enableDatabaseEncryption(TestEntityManager testEntityManager) {
            DatabaseEncryptionPasswordProperty.DATABASE_ENCRYPTION_PASSWORD = "MySuperSecretKey";
            DatabaseEncryptionPasswordProperty.DATABASE_ENCRYPTION_SALT= "deadbeef";
            testEntityManager.clear();
        }

        static void disableDatabaseEncryption(TestEntityManager testEntityManager) {
            DatabaseEncryptionPasswordProperty.DATABASE_ENCRYPTION_PASSWORD = null;
            DatabaseEncryptionPasswordProperty.DATABASE_ENCRYPTION_SALT= "deadbeef";
            testEntityManager.clear();
        }
    }

}