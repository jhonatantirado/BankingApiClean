package banking.customers.application.dao;

import java.util.List;

import banking.customers.domain.entity.Customer;

public interface ICustomerDAO {
	public List<Customer> getLoginCustomer(String user, String password);
}
