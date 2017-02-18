package io.ari.bucks.domain.repositories;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.google.common.collect.ImmutableMap;
import io.ari.bucks.domain.repositories.exceptions.BucksNotFoundException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.junit.MockServerRule;
import org.mockserver.model.Header;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.JsonBody.json;


@RunWith(MockitoJUnitRunner.class)
public class BucksRepositoryTest {

    @Before
    public void setUp() throws Exception {
        bucksRepository.setClient(client());
        bucksRepository.setContextRoot("http://localhost:1080");
    }

    @Rule
    public MockServerRule mockServerRule = new MockServerRule(this, 1080);

    @Test
    public void shouldReturnTheBucksInfoReturnedByTheService() throws BucksNotFoundException {
        new MockServerClient("localhost", 1080)
                .when(
                        request()
                                .withMethod("GET")
                                .withPath("/bucks")
                                .withHeader(
                                new Header("x-customer-id", CUSTOMER_ID))
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeaders(
                                        new Header("Content-Type", "application/json; charset=utf-8")
                                )
                                .withBody(json("{\"balance\":{\"total\":{\"value\":10, \"currency\":\"EUR\"}}}"))
                );


        Map<String, Object> bucks = bucksRepository.findByCustomerId(CUSTOMER_ID);

        assertEquals("The entity must be equals to the bucks", createBucks(), bucks);
    }

    @Test
    public void shouldReturnTheBucksIdWhenCreate() throws BucksNotFoundException {
        new MockServerClient("localhost", 1080)
                .when(
                        request()
                                .withMethod("POST")
                                .withPath("/bucks")
                                .withBody(json("{\"customerId\": \"12345678\"}"))
                )
                .respond(
                        response()
                                .withStatusCode(201)
                                .withHeaders(
                                        new Header("Content-Type", "application/json; charset=utf-8")
                                )
                                .withBody(json("{\"id\":\"123456\"}"))
                );


        String bucksId = bucksRepository.createBucks(CUSTOMER_ID);

        assertEquals("The entity must be equals to the bucks", "123456", bucksId);
    }

    @Test
    public void shouldDeleteTheBucks() throws BucksNotFoundException {
        new MockServerClient("localhost", 1080)
                .when(
                        request()
                                .withMethod("POST")
                                .withPath("/bucks")
                                .withBody(json("{\"customerId\": \"12345678\"}"))
                )
                .respond(
                        response()
                                .withStatusCode(201)
                                .withHeaders(
                                        new Header("Content-Type", "application/json; charset=utf-8")
                                )
                                .withBody(json("{\"id\":\"123456\"}"))
                );

        String bucksId = bucksRepository.createBucks(CUSTOMER_ID);

        bucksRepository.deleteByCustomerId(CUSTOMER_ID);

        new MockServerClient("localhost", 1080)
                .verify((
                        request()
                                .withMethod("DELETE")
                                .withPath("/bucks/" + bucksId)

                ));

    }

    @Test(expected = BucksNotFoundException.class)
    public void shouldReturnAnExceptionIfNotFound() throws BucksNotFoundException {
        new MockServerClient("localhost", 1080)
                .when(
                        request()
                                .withMethod("GET")
                                .withPath("/bucks")
                                .withHeader(
                                        new Header("x-customer-id", CUSTOMER_ID))
                )
                .respond(
                        response()
                                .withStatusCode(404)
                );


        bucksRepository.findByCustomerId(CUSTOMER_ID);
    }

    private Map<String, Object> createBucks() {
        return ImmutableMap.of("balance",
                ImmutableMap.of("total", ImmutableMap.of("value", 10, "currency", "EUR")));
    }

    private ObjectMapper jsonObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);
        mapper.enable(SerializationFeature.WRITE_BIGDECIMAL_AS_PLAIN);
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }

    private Client client() {
        Client client = ClientBuilder.newClient();
        client.register(new JacksonJsonProvider(jsonObjectMapper()));
        return client;
    }

    @InjectMocks
    private BucksRepository bucksRepository;

    private static String CUSTOMER_ID = "12345678";
}