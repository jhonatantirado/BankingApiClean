package banking.customers.infrastructure.hibernate.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import banking.common.infrastructure.hibernate.repository.BaseHibernateRepository;
import banking.customers.domain.entity.Customer;
import banking.customers.domain.repository.CustomerRepository;

@Repository
public class CustomerHibernateRepository extends BaseHibernateRepository<Customer> implements CustomerRepository {
	
	@Override
	public Customer create(Customer customer) throws Exception {		
		save(customer);		
		return (customer);
	}
	
	public void save(Customer customer) {
		super.save(customer);		
	}	
	
	
	
	
}