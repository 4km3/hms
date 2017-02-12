package io.ari.bucks.resources.assemblers;

import com.google.common.collect.ImmutableMap;
import io.ari.assemblers.HypermediaAssembler;
import io.ari.bucks.domain.Bucks;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static io.ari.money.MoneyBuilder.val;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BucksAssemblerTest {

    @Test
    public void shouldNotReturnNullDto() {
        Map<String, Object> bucksDto = bucksAssembler.convertEntitiesToDto(createBucks());
        assertNotNull("The bucks must be not null", bucksDto);
    }

    @Test
    public void shouldNotReturnMustHaveBalanceDto() {
        when(moneyAssembler.convertEntityToDto(val("10").eur().entity())).thenReturn(createMoney(TOTAL_TEST));
        Map<String, Object> bucksDto = bucksAssembler.convertEntitiesToDto(createBucks());

        Map<String, Object> balance = (Map<String, Object>) bucksDto.get("balance");

        assertNotNull("The bucks must be not null", balance);
        assertEquals("the total balance is not the expected", createMoney(TOTAL_TEST), balance.get("total"));
    }

    @Test
    public void shouldNotReturnMustHaveLimitsDto() {
        when(moneyAssembler.convertEntityToDto(val("10").eur().entity())).thenReturn(createMoney(RECHARGE_LIMIT_TEST));
        when(moneyAssembler.convertEntityToDto(val("0").eur().entity())).thenReturn(createMoney("0 EUR"));

        Map<String, Object> bucksDto = bucksAssembler.convertEntitiesToDto(createBucks());

        Map<String, Object> limits = (Map<String, Object>) bucksDto.get("limits");
        Map<String, Object> recharge = (Map<String, Object>) limits.get("recharge");

        assertNotNull("The recharge property  must be not null", recharge);

        assertEquals("the this period recharge limit is not the expected", createMoney(RECHARGE_LIMIT_TEST), recharge.get("thisPeriod"));
        assertEquals("the last recharge limit is not the expected", createMoney(TOTAL_LIMIT_TEST), recharge.get("last"));
        assertEquals("the remaining recharge limit is not the expected", createMoney("0 EUR"), recharge.get("remaining"));
        assertEquals("the max recharge limit is not the expected", createMoney(TOTAL_LIMIT_TEST), recharge.get("max"));
    }

    @Test
    public void shouldNotHaveParticipants() {
        Map<String, Object> bucksDto = bucksAssembler.convertEntitiesToDto(createBucks());

        Object participants = bucksDto.get("participants");
        assertNull("The participant must be null", participants);
    }

    @Test
    public void shouldHaveAListOfLinks() {
        Map<String, Object> bucksLink = new HashMap<>();
        Map<String, Object> hypermedia = new HashMap<>();
        hypermedia.put("self", bucksLink);

        when(hypermediaAssembler.createHypermedia("api/bucks", "ari-read")).thenReturn(hypermedia);

        Map<String, Object> bucksDto = bucksAssembler.convertEntitiesToDto(createBucks());
        Map<String, Object> links = (Map<String, Object>) bucksDto.get("_links");

        assertNotNull("The map of links must not be null", links);
        assertEquals("Hypermedia is not the expected", hypermedia, links);
    }

    private Bucks createBucks() {
        Bucks bucks = new Bucks(CUSTOMER_ID, BUCKS_ID);

        bucks.setTotalBalance(val("10").eur().entity());
        bucks.setMaxRechargeLimit(val("10").eur().entity());
        bucks.setThisPeriodRechargeLimit(val("10").eur().entity());
        bucks.setLastRecharge(val("10").eur().entity());

        bucks.setDaysTillMaxLimit(3);

        return bucks;
    }

    private Map<String, Object> createMoney(String money) {
        String[] splitMoney = money.split(" ");

        return ImmutableMap.of("value", new BigDecimal(splitMoney[0]),
                "currency", splitMoney[1]);
    }

    @InjectMocks
    private BucksAssembler bucksAssembler;

    @Mock
    private HypermediaAssembler hypermediaAssembler;

    private String CUSTOMER_ID = "5366bc483004a446a0b49dad";

    private String TOTAL_TEST = "10 EUR";

    private String RECHARGE_LIMIT_TEST = "10 EUR";

    private String TOTAL_LIMIT_TEST = "10 EUR";

    private String BUCKS_ID = "12345678";

    @Mock
    private MoneyAssembler moneyAssembler;
}

