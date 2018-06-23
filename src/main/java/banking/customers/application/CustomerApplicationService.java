package banking.customers.application;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import banking.common.application.Notification;
import banking.common.application.enumeration.RequestBodyType;
import banking.customers.application.dto.CustomerDto;
import banking.customers.domain.entity.Customer;
import banking.customers.domain.repository.CustomerRepository;


@Service()
public class CustomerApplicationService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Transactional
	public void performCreate(CustomerDto customerDto) throws Exception {
		Notification notification = this.validation(customerDto);
        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }	
        
        Customer customer = new Customer();        
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName()); 
       // customer.se(customerDto.getLastName());
        this.customerRepository.save(customer);
	}
	
	private Notification validation(CustomerDto customerDto) {
		Notification notification = new Notification();
		if (customerDto == null || customerDto.getRequestBodyType() == RequestBodyType.INVALID) {
			notification.addError("Invalid JSON data in request body.");
		}
		return notification;
	}
}
