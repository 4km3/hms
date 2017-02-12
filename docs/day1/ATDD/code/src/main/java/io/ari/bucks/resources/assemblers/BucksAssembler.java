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