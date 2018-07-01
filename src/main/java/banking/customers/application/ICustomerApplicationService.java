package banking.customers.application;

import java.util.List;

import banking.customers.domain.entity.Customer;

public interface ICustomerApplicationService {
	public List<Customer> getLoginCustomer(String user, String password);
}
