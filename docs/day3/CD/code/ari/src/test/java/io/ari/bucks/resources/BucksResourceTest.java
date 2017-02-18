package io.ari.bucks.resources;

import com.google.common.collect.ImmutableMap;
import io.ari.bucks.domain.repositories.BucksRepository;
import io.ari.bucks.domain.repositories.exceptions.BucksNotFoundException;
import io.ari.repositories.exceptions.EntityNotFound;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BucksResourceTest {

    @Test
    public void shouldReturnOkIfExistsCustomersBucks() throws EntityNotFound {
        ResponseEntity response = bucksResource.findBucks(CUSTOMER_ID);

        assertNotNull("The response must be not null", response);
        assertEquals("The response code should be OK", 200, response.getStatusCodeValue());
    }

    @Test
    public void shouldReturnTheBucksInfo() throws BucksNotFoundException {
        when(bucksRepository.findByCustomerId(CUSTOMER_ID)).thenReturn(createBucks());

        ResponseEntity response = bucksResource.findBucks(CUSTOMER_ID);

        Map<String, Object> entity = (Map<String, Object>) response.getBody();
        assertNotNull("The response entity must not null", entity);
        assertEquals("The entity is not the expected",createBucks(), entity);
    }

    @Test
    public void shouldReturnNotFoundWhenNotBucksDefined() throws BucksNotFoundException {
        when(bucksRepository.findByCustomerId(CUSTOMER_ID)).thenThrow(new BucksNotFoundException());
        ResponseEntity response = bucksResource.findBucks(CUSTOMER_ID);

        assertNotNull("The response must be not null", response);
        assertEquals("The response code should be OK", 404, response.getStatusCodeValue());

    }

    private Map<String, Object> createBucks() {
        return ImmutableMap.of("balance",
                ImmutableMap.of("total", ImmutableMap.of("value",10, "currency","EUR")));
    }

    @InjectMocks
    private BucksResource bucksResource;

    @Mock
    private BucksRepository bucksRepository;

    private static String CUSTOMER_ID = "12345678";
}