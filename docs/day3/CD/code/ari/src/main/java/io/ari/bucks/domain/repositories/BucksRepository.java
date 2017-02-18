package io.ari.bucks.domain.repositories;

import io.ari.bucks.domain.repositories.exceptions.BucksNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.Map;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Component
public class BucksRepository {

    public Map<String, Object> findBucksByCustomerId(String customerId) throws BucksNotFoundException {
        Response response = client.target(contextRoot)
                .path(URI)
                .request(APPLICATION_JSON)
                .header("x-customer-id",customerId)
                .get();

        if (response.getStatus() == 404){
            throw new BucksNotFoundException();
        }

        return response.readEntity(new GenericType<Map<String, Object>>() {
        });
    }

    protected void setContextRoot(String contextRoot) {
        this.contextRoot = contextRoot;
    }

    protected void setClient(Client client) {
        this.client = client;
    }

    private String contextRoot = "http://batta:8081";

    @Autowired
    private Client client;

    private String URI = "bucks";
}
