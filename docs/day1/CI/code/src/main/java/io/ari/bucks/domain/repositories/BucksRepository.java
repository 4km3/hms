package io.ari.bucks.domain.repositories;

import io.ari.bucks.domain.Bucks;
import io.ari.repositories.entities.EntitiesRepository;
import io.ari.repositories.exceptions.EntityNotFound;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class BucksRepository extends EntitiesRepository<Bucks> {

    public Bucks findByCustomerId(String customerId) throws EntityNotFound {
        return super.findById(customerBucks.get(customerId));
    }

    public Bucks findBucksByCustomerId(String customerId) throws EntityNotFound {
        return super.findById(customerBucks.get(customerId));
    }

    public Bucks save(Bucks bucks) {
        customerBucks.put(bucks.getCustomerId(), bucks.getId());
        return super.save(bucks);
    }

    public void deleteCustomer(String customerId) {
        super.delete(customerBucks.get(customerId));
    }

    private Map<String, String> customerBucks = new HashMap<>();
}
