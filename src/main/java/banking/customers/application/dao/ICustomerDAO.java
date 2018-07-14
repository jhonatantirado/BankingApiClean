package banking.customers.application.dao;

import java.util.List;

import banking.customers.application.dto.CustomerDto;
import banking.customers.domain.entity.Customer;
import banking.security.domain.entity.User;

public interface ICustomerDAO {
	public List<Customer> getLoginCustomer(String Message,String user, String password);	
	public List<Customer> getallCustomer(int offset, int limit);
	public List<Customer> getNrodocCustomer(String documentNumber);
	
	public List<CustomerDto> findByUserName(String username, String password);
}
