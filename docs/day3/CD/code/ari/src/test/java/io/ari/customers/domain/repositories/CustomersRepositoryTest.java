package io.ari.customers.domain.repositories;


import io.ari.bucks.domain.repositories.BucksRepository;
import io.ari.customers.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CustomersRepositoryTest {

    @Test
    public void shouldDeleteBucksWhenDeleteCustomer() {
        customersRepository.save(createCustomer());
        customersRepository.delete(CUSTOMER_ID);

        verify(bucksRepository).deleteByCustomerId(CUSTOMER_ID);
    }

    private Customer createCustomer() {
        return new Customer(CUSTOMER_ID, CARD_ID);
    }

    @InjectMocks
    private CustomersRepository customersRepository;

    @Mock
    private BucksRepository bucksRepository;

    private static String CUSTOMER_ID = "12345678";

    private static String CARD_ID = "2222222";
}
