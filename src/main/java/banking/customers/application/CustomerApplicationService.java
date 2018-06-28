package banking.customers.application;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import banking.common.application.Notification;
import banking.common.application.enumeration.RequestBodyType;
import banking.customers.application.dto.CustomerDto;
import banking.customers.domain.entity.Customer;
import banking.customers.domain.repository.CustomerRepository;
import banking.customers.domain.repository.CustomerRepositoryN;


@Service()
public class CustomerApplicationService {

	@Autowired
	private CustomerRepositoryN customerRepositoryN;
	
	@Transactional
	public void performCreate(CustomerDto customerDto) throws Exception {
		Notification notification = this.validation(customerDto);
        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }	
        
        Customer customer = new Customer();         
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName()); 
        customer.setActive(1);
        this.customerRepositoryN.save(customer);
	}	
	
	
	
	@Transactional
	public Customer save(Customer customer) {
		return customerRepositoryN.save(customer);
	}
	
	
	@Transactional
	public List<Customer> performCustomergetAll()  throws Exception {
		return this.customerRepositoryN.findAll();
	}
	
	@Transactional
	public Customer findOne(Long empid) {
		return customerRepositoryN.findOne(empid);
	}
	
	@Transactional
	public void delete(Customer customer) {
		customerRepositoryN.delete(customer);
	}
	
	private Notification validation(CustomerDto customerDto) {
		Notification notification = new Notification();
		if (customerDto == null || customerDto.getRequestBodyType() == RequestBodyType.INVALID) {
			notification.addError("Invalid JSON data in request body.");
		}
		return notification;
	}
}
