package banking.accounts.application.dto;

import java.math.BigDecimal;

import banking.common.application.dto.RequestBaseDto;
import banking.customers.application.dto.CustomerDto;
import banking.customers.domain.entity.Customer;

public class BankAccountDto extends RequestBaseDto {
	private long id;
	private String number;
	private BigDecimal balance;
	private long customer_id;
	//private CustomerDto customer;
	
	public Boolean getIslocked() {
		return islocked;
	}

	public void setIslocked(Boolean islocked) {
		this.islocked = islocked;
	}

	private Boolean islocked;
	

	public long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(long customer_id) {
		this.customer_id = customer_id;
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
}
