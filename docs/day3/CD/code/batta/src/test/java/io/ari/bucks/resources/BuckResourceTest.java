package io.ari.bucks.resources;

import io.ari.bucks.domain.repositories.BucksRepository;
import io.ari.repositories.exceptions.EntityNotFound;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BuckResourceTest {

    @Test
    public void shouldReturnNoContentIfExistsCustomersBucks() throws EntityNotFound {
        ResponseEntity response = buckResource.delete(BUCKS_ID);

        assertNotNull("The response must be not null", response);
        assertEquals("The response code should be NO CONTENT", 204, response.getStatusCodeValue());
    }

    @Test
    public void shouldDeleteBucks(){
        buckResource.delete(BUCKS_ID);
        verify(bucksRepository).delete(BUCKS_ID);
    }

    @InjectMocks
    private BuckResource buckResource;

    @Mock
    private BucksRepository bucksRepository;

    private static String BUCKS_ID = "1231231";
}
