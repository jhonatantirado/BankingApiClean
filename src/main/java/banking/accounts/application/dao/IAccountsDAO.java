package banking.accounts.application.dao;

import java.util.List;

import banking.accounts.domain.entity.BankAccount;


public interface IAccountsDAO {
	public List<BankAccount> getAccountIdCustomer(Long id_customer);
	
	public List<BankAccount> getallAccount(int offset, int limit);
	
	public List<BankAccount> getAccountNroCuenta(String accountNumber);
	
	
	
}
