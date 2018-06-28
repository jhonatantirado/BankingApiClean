package banking.customers.domain.repository;


import java.util.List;

import banking.customers.domain.entity.Customer;

public interface CustomerRepository {
	Customer create(Customer customer) throws Exception;	
	void save(Customer customer);
	
	void getId(Customer customer);		
	
}
