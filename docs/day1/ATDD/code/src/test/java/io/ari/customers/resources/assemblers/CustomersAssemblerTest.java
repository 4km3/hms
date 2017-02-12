package io.ari.customers.resources.assemblers;

import com.google.common.collect.ImmutableMap;
import io.ari.assemblers.HypermediaAssembler;
import io.ari.customers.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomersAssemblerTest {

    @Test
    public void shouldHaveACardId() {
        Customer customer = createCustomerEntity();

        Map<String, Object> customerDto = customersAssembler.convertEntityToDto(customer);
        assertEquals("The identity card is not the expected", CARD_ID, customerDto.get("idCard"));
    }

    @Test
    public void shouldHaveACustomerId() {
        Customer customer = createCustomerEntity();

        Map<String, Object> customerDto = customersAssembler.convertEntityToDto(customer);
        assertEquals("The customer id not the expected", CUSTOMER_ID, customerDto.get("id"));
    }

    @Test
    public void shouldHaveAName() {
        Customer customer = createCustomerEntity();

        Map<String, Object> customerDto = customersAssembler.convertEntityToDto(customer);
        assertEquals("The name is not the expected", NAME, customerDto.get("name"));
    }

    @Test
    public void shouldHaveAListOfContactDetails() {
        Customer customer = createCustomerEntity();
        Map<String, Object> customerDto = customersAssembler.convertEntityToDto(customer);

        Collection<Map<String, Object>> contactDetails = (Collection<Map<String, Object>>) customerDto.get("contactDetails");
        assertNotNull("There must be a contact details", contactDetails);
    }

    @Test
    public void shouldHaveAnEmailItem() {
        Customer customer = createCustomerEntity();
        Map<String, Object> customerDto = customersAssembler.convertEntityToDto(customer);

        Collection<Map<String, Object>> items = (Collection<Map<String, Object>>) customerDto.get("contactDetails");

        Map<String, Object> firstContactDetail = items.iterator().next();

        assertNotNull("The contact details must not be null", firstContactDetail);
        assertEquals("The contact details must be the expected", createEmailContact(), firstContactDetail);
    }

    @Test
    public void shouldHaveAPhoneItem() {
        Customer customer = createCustomerEntity();
        Map<String, Object> customerDto = customersAssembler.convertEntityToDto(customer);

        Collection<Map<String, Object>> items = (Collection<Map<String, Object>>) customerDto.get("contactDetails");

        Iterator<Map<String, Object>> iterator = items.iterator();
        iterator.next();
        Map<String, Object> secondContactDetail = iterator.next();

        assertNotNull("The contact details must not be null", secondContactDetail);
        assertEquals("The contact details must be the expected", createPhoneContact(), secondContactDetail);
    }

    @Test
    public void shouldHaveAListOfLinks() {
        Customer customer = createCustomerEntity();
        Map<String, Object> customerDto = customersAssembler.convertEntityToDto(customer);

        Map<String, Object> links = (Map<String, Object>) customerDto.get("_links");
        assertNotNull("The contact details must not be null", links);
    }

    @Test
    public void shouldHaveAMeOfLink() {
        Customer customer = createCustomerEntity();
        Map<String, Object> hypermedia = newHashMap();

        when(hypermediaAssembler.createHypermedia("api/me", "ari-read")).thenReturn(hypermedia);
        Map<String, Object> customerDto = customersAssembler.convertEntityToDto(customer);

        Map<String, Object> links = (Map<String, Object>) customerDto.get("_links");

        assertNotNull("The me link must be not null", links);

        assertEquals("The link has the href", hypermedia, links);
    }

    @Test
    public void shouldHaveABucksOfLink() {
        Customer customer = createCustomerEntity();
        Map<String, Object> hypermedia = newHashMap();
        hypermedia.put("href", "api/me");

        Map<String, Object> bucks = newHashMap();
        bucks.put("href", "api/bucks");

        when(hypermediaAssembler.createHypermedia("api/me", "ari-read")).thenReturn(hypermedia);
        when(hypermediaAssembler.createLink("api/bucks", "GET", "ari-read")).thenReturn(bucks);

        Map<String, Object> customerDto = customersAssembler.convertEntityToDto(customer);
        Map<String, Object> links = (Map<String, Object>) customerDto.get("_links");

        assertNotNull("The bucks link must be not null", links);

        Map<String, Object> link = (Map<String, Object>) links.get("bucks");

        assertEquals("The bucks link", bucks, link);
    }

    @Test
    public void shouldHaveARechargeLink() {
        Customer customer = createCustomerEntity();
        Map<String, Object> hypermedia = newHashMap();
        hypermedia.put("href", "api/me");

        Map<String, Object> expectedLink = newHashMap();
        expectedLink.put("href", "api/recharges");

        when(hypermediaAssembler.createHypermedia("api/me", "ari-read")).thenReturn(hypermedia);
        when(hypermediaAssembler.createLink("api/recharges", "POST", "ari-recharges")).thenReturn(expectedLink);

        Map<String, Object> customerDto = customersAssembler.convertEntityToDto(customer);
        Map<String, Object> links = (Map<String, Object>) customerDto.get("_links");

        assertNotNull("The recharge link must be not null", links);
        Map<String, Object> link = (Map<String, Object>) links.get("recharge");
        assertEquals("The recharge link must be the expected.", expectedLink, link);
    }

    @Test
    public void shouldHaveEditMeLink() {
        Customer customer = createCustomerEntity();
        Map<String, Object> hypermedia = newHashMap();
        hypermedia.put("href", "api/me");

        Map<String, Object> expectedLink = newHashMap();
        expectedLink.put("href", "api/me");

        when(hypermediaAssembler.createHypermedia("api/me", "ari-read")).thenReturn(hypermedia);
        when(hypermediaAssembler.createLink("api/me", "PUT", "ari-write")).thenReturn(expectedLink);

        Map<String, Object> customerDto = customersAssembler.convertEntityToDto(customer);
        Map<String, Object> links = (Map<String, Object>) customerDto.get("_links");

        assertNotNull("The links must be not null", links);
        Map<String, Object> link = (Map<String, Object>) links.get("editMe");
        assertEquals("The editMe link must be the expected.", expectedLink, link);
    }

    @Test
    public void shouldHaveEditSettingsLink() {
        Customer customer = createCustomerEntity();
        Map<String, Object> hypermedia = newHashMap();
        hypermedia.put("href", "api/me");

        Map<String, Object> expectedLink = newHashMap();
        expectedLink.put("href", "api/settings");

        when(hypermediaAssembler.createHypermedia("api/me", "ari-read")).thenReturn(hypermedia);
        when(hypermediaAssembler.createLink("api/settings", "PUT", "ari-write")).thenReturn(expectedLink);

        Map<String, Object> customerDto = customersAssembler.convertEntityToDto(customer);
        Map<String, Object> links = (Map<String, Object>) customerDto.get("_links");

        assertNotNull("The links must be not null", links);
        Map<String, Object> link = (Map<String, Object>) links.get("editSettings");
        assertEquals("The editSettings link must be the expected.", expectedLink, link);
    }

    @Test
    public void shouldHaveSettings() {
        Customer customer = createCustomerEntity();

        Map<String, Object> customerDto = customersAssembler.convertEntityToDto(customer);

        assertTrue("Returned customer dto cannot have a settings item.", customerDto.containsKey("settings"));
    }

    private Customer createCustomerEntity() {
        Customer customer = new Customer(CUSTOMER_ID, CARD_ID);

        customer.setName(NAME);
        customer.setLastName("Hitchcock");

        customer.setAddress(createAddress());
        customer.setEmail("alfred.hitchcock@indiscreet.com");
        customer.setMobilePhone("+3492123123");
        customer.setSettings(ImmutableMap.of("dummySettings", true));

        return customer;
    }

    private Map<String, Object> createAddress() {
        Map<String, Object> adress = newHashMap();

        adress.put("addressType", "street");
        adress.put("streetAddress", "Maldonado");
        adress.put("streetNumber", 1);
        adress.put("houseNumber", 3);
        adress.put("houseLetter", "A");
        adress.put("postcode", "28006");
        adress.put("town", "Madrid");

        return adress;
    }

    private Map<String, Object> createEmailContact() {
        Map<String, Object> emailContact = newHashMap();

        emailContact.put("contact", "alfred.hitchcock@indiscreet.com");
        emailContact.put("contactType", "personal_email");

        return emailContact;
    }

    private Map<String, Object> createPhoneContact() {
        Map<String, Object> phoneContact = newHashMap();

        phoneContact.put("contact", "+3492123123");
        phoneContact.put("contactType", "personal_phone");

        return phoneContact;
    }

    @InjectMocks
    private CustomersAssembler customersAssembler;

    @Mock
    private HypermediaAssembler hypermediaAssembler;

    private static final String CUSTOMER_ID = "0000001";

    private static final String NAME = "Alfred";

    private static final String CARD_ID = "123123231X";
}
