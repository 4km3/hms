package io.ari.bucks.resources;

import com.google.common.collect.ImmutableMap;
import io.ari.bucks.domain.Bucks;
import io.ari.bucks.domain.factories.BucksFactory;
import io.ari.bucks.domain.repositories.BucksRepository;
import io.ari.bucks.resources.assemblers.BucksAssembler;
import io.ari.repositories.exceptions.EntityNotFound;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.executable.ValidateOnExecution;
import java.util.Map;

@RestController
@RequestMapping("bucks")
public class BucksResource {

	@RequestMapping(method = RequestMethod.GET)
	@ValidateOnExecution
	public ResponseEntity findBucks(@RequestHeader("x-customer-id") @NotEmpty String customerId) {
		try {
			return ResponseEntity.ok()
					.body(bucksAssembler
							.convertEntitiesToDto(bucksRepository.findByCustomerId(customerId)));
		} catch (EntityNotFound entityNotFound) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	@ValidateOnExecution
	public ResponseEntity createBucks(@NotEmpty @RequestBody Map<String, Object> customerIdMap) {
		Bucks bucks = bucksFactory.createBucks(customerIdMap.get("customerId").toString());
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ImmutableMap.of("id",bucks.getId()));
	}

	@Autowired
	private BucksRepository bucksRepository;
	
	@Autowired
	private BucksAssembler bucksAssembler;

	@Autowired
	private BucksFactory bucksFactory;
}
