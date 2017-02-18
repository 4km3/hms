package io.ari.bucks.resources;

import io.ari.bucks.domain.repositories.BucksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bucks/{bucksId}")
public class BuckResource {

    public ResponseEntity delete(@RequestParam("buckId") String bucksId) {
        bucksRepository.delete(bucksId);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @Autowired
    private BucksRepository bucksRepository;
}
