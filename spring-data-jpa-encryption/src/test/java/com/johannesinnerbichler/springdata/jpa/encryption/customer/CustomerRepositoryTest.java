package com.johannesinnerbichler.springdata.jpa.encryption.customer;

import com.johannesinnerbichler.spring.data.jpa.encryption.JPAEncryptionApplication;
import com.johannesinnerbichler.spring.data.jpa.encryption.converters.DatabaseEncryptionPasswordProperty;
import com.johannesinnerbichler.spring.data.jpa.encryption.customer.Customer;
import com.johannesinnerbichler.spring.data.jpa.encryption.customer.CustomerRepository;
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
        Customer firstPersist = customerRepository.save(customer);
        Customer secondCustomer = new Customer();

        Customer secondPersist = customerRepository.save(secondCustomer);

        assertThat(secondPersist.getId()).isEqualTo(firstPersist.getId() + 1);
    }

    @Test
    public void save_should_verify_that_encryption_is_enabled_on_first_name_field() {
        String plainFirstName = "Johannes";
        customer.setFirstName(plainFirstName);
        Customer savedCustomerWithEncryptionEnabled = customerRepository.save(customer);
        disableDatabaseEncryption(testEntityManager);

        Customer customerRetrievedWithoutEncryptionEnabled = testEntityManager.find(Customer.class, savedCustomerWithEncryptionEnabled.getId());

        assertThat(customerRetrievedWithoutEncryptionEnabled.getFirstName())
                .isNotEqualTo(plainFirstName)
                .isEqualTo("73d51abbd89cb8196f0efb6892f94d684b9bb00e302a79defe4bc621a65f1f0f");
    }

    @Test
    public void save_should_verify_that_encryption_is_enabled_on_last_name_field() {
        String plainLastName = "Innerbichler";
        customer.setLastName(plainLastName);
        Customer savedCustomerWithEncryptionEnabled = customerRepository.save(customer);
        disableDatabaseEncryption(testEntityManager);

        Customer customerRetrievedWithoutEncryptionEnabled = testEntityManager.find(Customer.class, savedCustomerWithEncryptionEnabled.getId());

        assertThat(customerRetrievedWithoutEncryptionEnabled.getLastName())
                .isNotEqualTo(plainLastName)
                .isEqualTo("73d51abbd89cb8196f0efb6892f94d68b406039fa8488b90b4dce1374348607f");
    }

    @Test
    public void save_should_verify_that_encryption_is_enabled_on_email_field() {
        String plainEmail = "j.innerbichler@gmail.com";
        customer.setEmail(plainEmail);
        Customer savedCustomerWithEncryptionEnabled = customerRepository.save(customer);
        disableDatabaseEncryption(testEntityManager);

        Customer customerRetrievedWithoutEncryptionEnabled = testEntityManager.find(Customer.class, savedCustomerWithEncryptionEnabled.getId());

        assertThat(customerRetrievedWithoutEncryptionEnabled.getEmail())
                .isNotEqualTo(plainEmail)
                .isEqualTo("73d51abbd89cb8196f0efb6892f94d68894ed82ec367a8c562cfd884c93a5578bd96a01b8c0607f80adda09c5805d297");
    }

    final static class Helper {

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