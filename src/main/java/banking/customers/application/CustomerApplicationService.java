package banking.customers.application;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import banking.common.api.controller.Utilidades;
import banking.common.application.Notification;
import banking.common.application.enumeration.RequestBodyType;
import banking.customers.application.dao.CustomerDAO;
import banking.customers.application.dto.CustomerDto;
import banking.customers.domain.entity.Customer;
import banking.customers.domain.repository.CustomerRepository;
import banking.customers.domain.repository.CustomerRepositoryN;


@Service()
public class CustomerApplicationService implements ICustomerApplicationService {

	@Autowired
	private CustomerRepositoryN customerRepositoryN;
	
	@Autowired
	CustomerDAO customerDAO;
	
	@Transactional
	public ResponseEntity<Object> performCreateCustomer(CustomerDto customerDto) throws Exception {		
		Notification notification = this.validation(customerDto);
        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }        
        String EnriptarClave = Utilidades.Encriptar(customerDto.getPassword());        
		Customer customer = new Customer(); 
		customer.setFirstName(customerDto.getFirstName());
		customer.setLastName(customerDto.getLastName());
		customer.setBirthDate(customerDto.getBirthDate());
		customer.setDocumentNumber(customerDto.getDocumentNumber());
		customer.setIsactive(false);
		customer.setCellphone(customerDto.getCellphone());
		customer.setEmail(customerDto.getEmail());
		customer.setUser(customerDto.getUser());
		customer.setPassword(EnriptarClave);
		customer.setId_rol(customerDto.getId_rol());		
		Customer CreateCustomer= this.save(customer);		
		return ResponseEntity.ok().body(CreateCustomer);				
	}	
	
	
	
	@Transactional
	public ResponseEntity<Object> performUpdateCustomer(CustomerDto customerDto, Long customerid) throws Exception {
		Notification notification = this.validation(customerDto);
        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }
		Customer customer = this.findOne(customerid);
		if(customer==null) {
			return ResponseEntity.notFound().build();
		}		
		String EnriptarClave = Utilidades.Encriptar(customerDto.getPassword());
		customer.setFirstName(customerDto.getFirstName());
		customer.setLastName(customerDto.getLastName());
		customer.setBirthDate(customerDto.getBirthDate());
		customer.setDocumentNumber(customerDto.getDocumentNumber());
		customer.setIsactive(customerDto.getIsactive());
		customer.setCellphone(customerDto.getCellphone());
		customer.setEmail(customerDto.getEmail());
		customer.setUser(customerDto.getUser());
		customer.setPassword(EnriptarClave);
		customer.setId_rol(customerDto.getId_rol());		
		Customer updateEmployee= this.save(customer);		
		return ResponseEntity.ok().body(updateEmployee);
		
	}	
	
		
	@Transactional
	public ResponseEntity<Object> performGetCustomerId(int customerid) throws Exception {
		CustomerDto customerDto = new CustomerDto();
		customerDto.setId(customerid);		
		Notification notification = this.validation(customerDto);
        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }		
		Customer customer=this.findOne(Long.valueOf(customerid));		
		if(customer==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(customer);
	}
	
	@Transactional
	public ResponseEntity<Customer> performGetAllCustomer(Customer customer) throws Exception {
		return   (ResponseEntity<Customer>) this.customerRepositoryN.findAll();
	}
	
	@Transactional
	public ResponseEntity<Object> performDelCustomerId(int customerid) throws Exception {
		CustomerDto customerDto = new CustomerDto();
		customerDto.setId(customerid);		
		Notification notification = this.validation(customerDto);
        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }		
		Customer customer=this.findOne(Long.valueOf(customerid));
		if(customer==null) {
			return ResponseEntity.notFound().build();
		}
		this.delete(customer);		
		return ResponseEntity.ok().body(customer);
	}
	

	@Transactional
	public Customer save(Customer customer) {
		return customerRepositoryN.save(customer);
	}
	
	
	@Transactional
	public List<Customer> performCustomergetAll(int offset, int limit)  throws Exception {
		return customerRepositoryN.findAll();
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

	@Transactional
	public List<Customer> getLoginCustomer(String Message,String user,String password) {
		 String EnriptarClave = Utilidades.Encriptar(password);
		return customerDAO.getLoginCustomer(Message,user,EnriptarClave);		
	}
	
	@Transactional
	public List<Customer> getallCustomer(int offset, int limit) {
		return customerDAO.getallCustomer(offset,limit);		
	}
	
	@Transactional
	public List<Customer> getNrodocCustomer(String documentNumber) {
		return customerDAO.getNrodocCustomer(documentNumber);		
	}



	

	
	
	
	
}
