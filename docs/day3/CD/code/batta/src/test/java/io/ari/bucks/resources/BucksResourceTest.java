package io.ari.bucks.resources;

import com.google.common.collect.ImmutableMap;
import io.ari.bucks.domain.Bucks;
import io.ari.bucks.domain.factories.BucksFactory;
import io.ari.bucks.domain.repositories.BucksRepository;
import io.ari.bucks.resources.assemblers.BucksAssembler;
import io.ari.bucks.resources.assemblers.MoneyAssembler;
import io.ari.repositories.exceptions.EntityNotFound;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Map;

import static io.ari.money.MoneyBuilder.val;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
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
    public void shouldReturnTheBucksInfo() throws EntityNotFound {
        when(bucksRepository.findByCustomerId(CUSTOMER_ID)).thenReturn(bucks);
        when(bucksAssembler.convertEntitiesToDto(bucks)).thenReturn(createBucks());

        ResponseEntity response = bucksResource.findBucks(CUSTOMER_ID);

        Map<String, Object> entity = (Map<String, Object>) response.getBody();

        assertNotNull("The response entity must not null", entity);
        assertEquals("The entity is not the expected",createBucks(), entity);
    }

    @Test
    public void shouldReturnNotFoundWhenNotBucksDefined() throws EntityNotFound {
        when(bucksRepository.findByCustomerId(CUSTOMER_ID)).thenThrow(new EntityNotFound());
        ResponseEntity response = bucksResource.findBucks(CUSTOMER_ID);

        assertNotNull("The response must be not null", response);
        assertEquals("The response code should be OK", 404, response.getStatusCodeValue());

    }

    @Test
    public void shouldReturnCreateBucks() throws EntityNotFound {
        Map<String,Object> customerIdMap = ImmutableMap.of("customerId",CUSTOMER_ID);
        when(bucksFactory.createBucks(CUSTOMER_ID)).thenReturn(bucks);
        when(bucks.getId()).thenReturn(BUCKS_ID);


        ResponseEntity response = bucksResource.createBucks(customerIdMap);

        assertNotNull("The response must be not null", response);
        assertEquals("The response code should be CREATED", 201, response.getStatusCodeValue());
    }

    @Test
    public void shouldReturnTheBucksIdentifier() throws EntityNotFound {
        Map<String,Object> customerIdMap = ImmutableMap.of("customerId",CUSTOMER_ID);
        when(bucksFactory.createBucks(CUSTOMER_ID)).thenReturn(bucks);
        when(bucks.getId()).thenReturn(BUCKS_ID);

        ResponseEntity response = bucksResource.createBucks(customerIdMap);

        Map<String, Object> entity = (Map<String, Object>) response.getBody();

        assertEquals("The response is not the expected",entity, ImmutableMap.of("id",BUCKS_ID));
    }


    private Map<String, Object> createBucks() {
        return ImmutableMap.of("balance",
                ImmutableMap.of("total", ImmutableMap.of("value",10, "currency","EUR")));
    }

    @InjectMocks
    private BucksResource bucksResource;

    @Mock
    private BucksRepository bucksRepository;

    @Mock
    private BucksAssembler bucksAssembler;

    @Mock
    private BucksFactory bucksFactory;

    @Mock
    private Bucks bucks;

    private static String CUSTOMER_ID = "12345678";

    private static String BUCKS_ID = "22223-12345678";

}