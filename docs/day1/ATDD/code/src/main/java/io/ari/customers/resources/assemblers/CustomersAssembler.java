package io.ari.customers.resources.assemblers;

import io.ari.assemblers.HypermediaAssembler;
import io.ari.customers.domain.Customer;
import io.ari.customers.domain.exceptions.CustomerExists;
import io.ari.customers.domain.factories.CustomersFactory;
import io.ari.customers.resources.assemblers.exceptions.InvalidIdCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.collect.ImmutableMap.of;
import static com.google.common.collect.Maps.newHashMap;
import static java.util.stream.Collectors.toMap;

@Component
public class CustomersAssembler {

    public Customer convertDtoToEntity(String customerId, Map<String, Object> customerData) throws CustomerExists, InvalidIdCard {
        String idCard = (String) customerData.get("idCard");

        if (!idCardValidator.isValid(idCard)) {
            throw new InvalidIdCard();
        }

        Customer customer = customersFactory.createCustomer(customerId,
                idCard,
                (String) customerData.get("name"),
                (String) customerData.get("lastName"),
                (String) customerData.get("mobilePhone"));

        customer.setEmail((String) customerData.get("email"));
        customer.setAvatar((String) customerData.get("avatar"));

        return customer;
    }

    public Map<String, Object> convertEntityToDto(Customer customer) {
        Map<String, Object> customerDto = new HashMap<>();

        customerDto.put("id", customer.getId());
        customerDto.put("idCard", customer.getIdCard());
        customerDto.put("settings", customer.getSettings());
        customerDto.put("name", customer.getName());
        customerDto.put("lastName", customer.getLastName());
        customerDto.put("contactDetails", createContacts(customer));
        customerDto.put("email",customer.getEmail());
        customerDto.put("address",customer.getAddress());

        customerDto.put("_links", getCustomerHypermedia());

        return customerDto;
    }

    private Collection<Map<String, Object>> createContacts(Customer customer) {
        Collection<Map<String, Object>> contacts = new ArrayList<>();

        contacts.add(createEmailContact(customer.getEmail()));
        contacts.add(createPhoneContact(customer.getMobilePhone()));

        return contacts;
    }

    private Map<String, Object> createEmailContact(String email) {
        Map<String, Object> emailContact = newHashMap();

        emailContact.put("contact", email);
        emailContact.put("contactType", "personal_email");

        return emailContact;
    }

    private Map<String, Object> createPhoneContact(String phoneNumber) {
        Map<String, Object> phoneContact = newHashMap();

        phoneContact.put("contact", phoneNumber);
        phoneContact.put("contactType", "personal_phone");

        return phoneContact;
    }

    private Map<String, Object> getCustomerHypermedia() {
        Map<String, Object> hypermedia = hypermediaAssembler.createHypermedia("api/me", "ari-read");

        hypermedia.put("bucks", hypermediaAssembler.createLink("api/bucks", "GET", "ari-read"));
        hypermedia.put("settings", hypermediaAssembler.createLink("api/settings", "GET", "ari-read"));
        hypermedia.put("editSettings", hypermediaAssembler.createLink("api/settings", "PUT", "ari-write"));
        hypermedia.put("editMe", hypermediaAssembler.createLink("api/me", "PUT", "ari-write"));

        hypermedia.put("recharge", hypermediaAssembler.createLink("api/recharges", "POST", "ari-recharges"));

        return hypermedia;
    }

    @Autowired
    private CustomersFactory customersFactory;

    @Autowired
    private HypermediaAssembler hypermediaAssembler;

    @Autowired
    private IdCardValidator idCardValidator;

}
