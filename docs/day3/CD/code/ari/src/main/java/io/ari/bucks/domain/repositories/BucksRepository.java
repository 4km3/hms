package io.ari.bucks.domain.repositories;

import com.google.common.collect.ImmutableMap;
import io.ari.bucks.domain.repositories.exceptions.BucksNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

import static javax.ws.rs.client.Entity.json;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Component
public class BucksRepository {

    public Map<String, Object> findByCustomerId(String customerId) throws BucksNotFoundException {
        Response response = createBuilder()
                .header("x-customer-id", customerId)
                .get();

        if (response.getStatus() == 404) {
            throw new BucksNotFoundException();
        }

        return response.readEntity(new GenericType<Map<String, Object>>() {
        });
    }

    public String createBucks(String customerId) {
        Response response = createBuilder().post(json(ImmutableMap.of("customerId", customerId)));

        Map<String, Object> bucksId = response.readEntity(new GenericType<Map<String, Object>>() {
        });

        customerBucks.put(customerId, bucksId.get("id").toString());
        return bucksId.get("id").toString();
    }

    private Invocation.Builder createBuilder() {
        return client.target(contextRoot)
                .path(URI)
                .request(APPLICATION_JSON);
    }

    public void deleteByCustomerId(String customerId) {
        client
                .target(contextRoot)
                .path("/bucks/" + customerBucks.get(customerId))
                .request(APPLICATION_JSON)
                .delete();

    }

    private Map<String, String> customerBucks = new HashMap<>();

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
