package io.ari.bucks.domain.factories;


import io.ari.bucks.domain.Bucks;
import io.ari.bucks.domain.repositories.BucksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BucksFactory {

    public Bucks createBucks(String customerId) {
        seq++;
        Bucks newBucks = new Bucks(customerId, seq.toString());
        return bucksRepository.save(newBucks);
    }

    private Integer seq = 0;

    @Autowired
    private BucksRepository bucksRepository;

}
