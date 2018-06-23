package banking.customers.application.dto;

import java.util.Set;

import banking.accounts.application.dto.BankAccountDto;
import banking.common.application.dto.RequestBaseDto;
import banking.common.application.enumeration.RequestBodyType;

public class CustomerDto  extends RequestBaseDto {
	private String firstName;
	private String lastName;
	private Set<BankAccountDto> bankAccountsDto;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<BankAccountDto> getBankAccountsDto() {
		return bankAccountsDto;
	}

	public void setBankAccountsDto(Set<BankAccountDto> bankAccountsDto) {
		this.bankAccountsDto = bankAccountsDto;
	}

	
}
