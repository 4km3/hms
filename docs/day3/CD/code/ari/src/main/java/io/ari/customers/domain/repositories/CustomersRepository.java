package io.ari.customers.domain.repositories;

import io.ari.bucks.domain.repositories.BucksRepository;
import io.ari.customers.domain.Customer;
import io.ari.repositories.entities.EntitiesRepository;
import io.ari.repositories.exceptions.EntityNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class CustomersRepository extends EntitiesRepository<Customer> {

	public Optional<Customer> findByMobilePhone(String mobilePhone) {
		try {
			return Optional.of(super.findById(mobilePhones.get(mobilePhone)));
		} catch (EntityNotFound entityNotFound) {
			return Optional.empty();
		}
	}

	public Optional<Customer> findByIdCard(String idCard) {
		try {
			return Optional.of(super.findById(idCards.get(idCard)));
		} catch (EntityNotFound entityNotFound) {
			return Optional.empty();
		}
	}

	public Customer save(Customer customer){
		idCards.put(customer.getIdCard(),customer.getId());
		mobilePhones.put(customer.getMobilePhone(),customer.getId());

		return super.save(customer);
	}

	public void delete(String id) {
		try {
			Customer customer = findById(id);

			bucksRepository.deleteByCustomerId(id);
			idCards.remove(customer.getIdCard());
			mobilePhones.remove(customer.getMobilePhone());

			super.delete(id);
		} catch (EntityNotFound entityNotFound) {
			entityNotFound.printStackTrace();
		}
	}

	private Map<String,String> idCards = new HashMap<>();

	private Map<String,String> mobilePhones = new HashMap<>();

	@Autowired
	private BucksRepository bucksRepository;
}
