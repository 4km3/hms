package io.ari.bucks.domain;


import io.ari.repositories.entities.Entity;

import java.math.BigDecimal;

public class Bucks implements Entity {

	public Bucks(String customerId, String agreementId) {
		this.id = agreementId;
		this.customerId = customerId;
	}

	public void receiveMoney(Money amount) {
		thisPeriodRechargeLimit = thisPeriodRechargeLimit.add(amount);
		lastRecharge = amount;
		
		totalBalance = totalBalance.add(amount);
	}

	public void refund(Money money) {
		totalBalance = totalBalance.add(money);
	}

	public Money getRemainingRechargeLimit() {
		return maxRechargeLimit.subtract(thisPeriodRechargeLimit);
	}

	public Money getMaxRechargeLimit() {
		return maxRechargeLimit;
	}

	public void setMaxRechargeLimit(Money maxRechargeLimit) {
		this.maxRechargeLimit = maxRechargeLimit;
	}

	public Money getLastRecharge() {
		return lastRecharge;
	}

	public void setLastRecharge(Money lastRecharge) {
		this.lastRecharge = lastRecharge;
	}

	public Money getThisPeriodRechargeLimit() {
		return thisPeriodRechargeLimit;
	}

	public void setThisPeriodRechargeLimit(Money thisPeriodRechargeLimit) {
		this.thisPeriodRechargeLimit = thisPeriodRechargeLimit;
	}

	public Integer getDaysTillMaxLimit() {
		return daysTillMaxLimit;
	}

	public void setDaysTillMaxLimit(Integer daysTillMaxLimit) {
		this.daysTillMaxLimit = daysTillMaxLimit;
	}

	public Money getTotalBalance() {
		return totalBalance;
	}

	public void setTotalBalance(Money totalBalance) {
		this.totalBalance = totalBalance;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerId() {
		return customerId;
	}
	
	@Override
	public String toString() {
		return "Bucks [totalBalance=" + totalBalance
				+ ", maxRechargeLimit=" + maxRechargeLimit + ", lastRechargeLimit="
				+ lastRecharge + ", thisPeriodRechargeLimit=" + thisPeriodRechargeLimit + ", daysTillMaxLimit=" + daysTillMaxLimit + ", id="
				+ id + "]";
	}

	private Money totalBalance = new Money(BigDecimal.ZERO, "EUR");

	private Money maxRechargeLimit = new Money(new BigDecimal("2500"), "EUR");

	private Money lastRecharge = new Money(BigDecimal.ZERO, "EUR");

	private Money thisPeriodRechargeLimit = new Money(BigDecimal.ZERO, "EUR");

	private Integer daysTillMaxLimit = 0;

	private String id;

	private String customerId;
}
