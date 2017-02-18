package io.ari.customers.domain.factories;


import io.ari.bucks.domain.repositories.BucksRepository;
import io.ari.customers.domain.Customer;
import io.ari.customers.domain.exceptions.CustomerExists;
import io.ari.customers.domain.exceptions.CustomerIdCardExists;
import io.ari.customers.domain.exceptions.CustomerIdExists;
import io.ari.customers.domain.exceptions.CustomerMobilePhoneExists;
import io.ari.customers.domain.repositories.CustomersRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomersFactoryTest {

    @Before
    public void prepareCustomerRepository(){
        when(customersRepository.exists(ID)).thenReturn(false);
        when(customersRepository.findByMobilePhone(MOBILE_PHONE)).thenReturn(Optional.empty());
        when(customersRepository.findByIdCard(ID_CARD)).thenReturn(Optional.empty());
    }

    @Test
    public void shouldSaveTheCustomer() throws CustomerExists {
        Customer customer = customerFactory.createCustomer(ID, ID_CARD, NAME, LAST_NAME, MOBILE_PHONE);
        verify(customersRepository).save(customer);
    }

    @Test
    public void shouldCreateACustomerWithCorrectId() throws CustomerExists {
        Customer customer = customerFactory.createCustomer(ID, ID_CARD, NAME, LAST_NAME, MOBILE_PHONE);

        assertNotNull("Created customer must have id.", customer.getId());
        assertEquals("Created customer must have the right id.", ID, customer.getId());
    }

    @Test
    public void shouldCreateACustomerWithCorrectName() throws CustomerExists {
        Customer customer = customerFactory.createCustomer(ID, ID_CARD, NAME, LAST_NAME, MOBILE_PHONE);

        assertNotNull("Created customer must have name.", customer.getName());
        assertEquals("Created customer must have the right name.", NAME, customer.getName());
    }

    @Test
    public void shouldCreateBucksWhenCreateCustomer() throws CustomerExists {
        customerFactory.createCustomer(ID, ID_CARD, NAME, LAST_NAME, MOBILE_PHONE);

        verify(bucksRepository).createBucks(ID);
    }

    @Test
    public void shouldCreateACustomerWithCorrectLastName() throws CustomerExists {
        Customer customer = customerFactory.createCustomer(ID, ID_CARD, NAME, LAST_NAME, MOBILE_PHONE);

        assertNotNull("Created customer must have last name.", customer.getLastName());
        assertEquals("Created customer must have the right last name.", LAST_NAME, customer.getLastName());
    }

    @Test
    public void shouldCreateACustomerWithCorrectMobile() throws CustomerExists {
        Customer customer = customerFactory.createCustomer(ID, ID_CARD, NAME, LAST_NAME, MOBILE_PHONE);

        assertNotNull("Created customer must have mobile.", customer.getMobilePhone());
        assertEquals("Created customer must have the right mobile.", MOBILE_PHONE, customer.getMobilePhone());
    }

    @Test(expected = CustomerIdExists.class)
    public void shouldThrowExceptionIfCustomerWithIdExists() throws CustomerExists {
        when(customersRepository.exists(ID)).thenReturn(true);
        customerFactory.createCustomer(ID, ID_CARD, NAME, LAST_NAME, MOBILE_PHONE);
    }

    @Test(expected = CustomerMobilePhoneExists.class)
    public void shouldThrowExceptionIfCustomerMobileExists() throws CustomerExists {
        when(customersRepository.findByMobilePhone(MOBILE_PHONE)).thenReturn(Optional.of(customer));
        customerFactory.createCustomer(ID, ID_CARD, NAME, LAST_NAME, MOBILE_PHONE);
    }

    @Test(expected = CustomerIdCardExists.class)
    public void shouldThrowExceptionIfCardIdExists() throws CustomerExists {
        when(customersRepository.findByIdCard(ID_CARD)).thenReturn(Optional.of(customer));
        customerFactory.createCustomer(ID, ID_CARD, NAME, LAST_NAME, MOBILE_PHONE);
    }

    @InjectMocks
    private CustomersFactory customerFactory;

    @Mock
    private CustomersRepository customersRepository;

    @Mock
    private BucksRepository bucksRepository;

    @Mock
    private Customer customer;

    private static final String ID = "da976a97d59a7d";

    private static final String ID_CARD = "11111111H";

    private static final String NAME = "Alfred";

    private static final String LAST_NAME = "Smith";

    private static final String MOBILE_PHONE = "798612123";
}
