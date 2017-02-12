package io.ari.bucks.resources.assemblers;

import com.google.common.collect.ImmutableMap;
import io.ari.assemblers.HypermediaAssembler;
import io.ari.bucks.domain.Bucks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class BucksAssembler {

    public Map<String, Object> convertEntitiesToDto(Bucks bucks) {
        HashMap<String, Object> bucksDto = new HashMap<>();

        bucksDto.put("id", bucks.getId());
        bucksDto.put("customerId", bucks.getCustomerId());
        bucksDto.put("balance", createBalance(bucks));
        bucksDto.put("limits", createLimits(bucks));

        bucksDto.put("_links", hypermediaAssembler.createHypermedia(SELF_URI, "ari-read"));

        return bucksDto;
    }

    private Map<String, Object> createBalance(Bucks bucks) {
        return ImmutableMap.of("total", moneyAssembler.convertEntityToDto(bucks.getTotalBalance()));
    }

    private Map<String, Object> createLimits(Bucks bucks) {
        return ImmutableMap.of("recharge",createRechargeLimits(bucks));
    }

    private Map<String, Object> createRechargeLimits(Bucks bucks) {
        return ImmutableMap.of("thisPeriod", moneyAssembler.convertEntityToDto(bucks.getThisPeriodRechargeLimit()),
                "last", moneyAssembler.convertEntityToDto(bucks.getLastRecharge()),
                "remaining", moneyAssembler.convertEntityToDto(bucks.getRemainingRechargeLimit()),
                "max",  moneyAssembler.convertEntityToDto(bucks.getMaxRechargeLimit()));
    }

    private static final String SELF_URI = "api/bucks";

    @Autowired
    private HypermediaAssembler hypermediaAssembler;

    @Autowired
    private MoneyAssembler moneyAssembler;

}
/*
    assertTrue("Recharge limits must have property thisPeriod", recharge.containsKey("thisPeriod"));
        assertEquals("Recharge this period limit must be the expected.", moneyFactory.createMoney((String) expectedLimitsData.get("rechargeThisPeriod")), recharge.get("thisPeriod"));
        assertTrue("Recharge limits must have property last", recharge.containsKey("last"));
        assertEquals("Recharge last limit must be the expected.", moneyFactory.createMoney((String) expectedLimitsData.get("rechargeLast")), recharge.get("last"));
        assertTrue("Recharge limits must have property remaining", recharge.containsKey("remaining"));
        assertEquals("Recharge remaining limit must be the expected.", moneyFactory.createMoney((String) expectedLimitsData.get("rechargeRemaining")), recharge.get("remaining"));
        assertTrue("Recharge limits must have property max", recharge.containsKey("max"));
        assertEquals("Recharge max limit must be the expected.", moneyFactory.createMoney((String) expectedLimitsData.get("rechargeMax")), recharge.get("max"));
*/