package banking.customers.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import banking.common.api.controller.ResponseHandler;
import banking.customers.application.CustomerApplicationService;
import banking.customers.application.dto.CustomerDto;
import banking.customers.domain.entity.Customer;

@RestController
@RequestMapping("api/customers/")
public class CustomerController{		
		@Autowired
		CustomerApplicationService customerApplicationService;
		
		@Autowired
		ResponseHandler responseHandler;		
				
		//CrearCustomer				
		@CrossOrigin(origins = "http://localhost:4200")
		@RequestMapping(method = RequestMethod.POST, path = "customer", consumes = "application/json; charset=UTF-8", produces = "application/json; charset=UTF-8")
		public ResponseEntity<Object> createEmployee(@Valid @RequestBody CustomerDto customerDto) throws Exception {
			try {
			return customerApplicationService.performCreateCustomer(customerDto);  			
			} catch(IllegalArgumentException ex) {
				return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
			} catch(Exception ex) {
				return this.responseHandler.getAppExceptionResponse();
			}
		}		
		
		//ListaIdCustomer
		@CrossOrigin(origins = "http://localhost:4200")	
		@GetMapping("/customer/{CustomerId}")
		public ResponseEntity<Object> getCustomerById(@PathVariable(value="CustomerId") int customerid){
		try {				
				return customerApplicationService.performGetCustomerId(customerid);				
			} catch(IllegalArgumentException ex) {
				return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
			} catch(Exception ex) {
				return this.responseHandler.getAppExceptionResponse();
			}
		}
		
		//UpdateIdCustomer
		@PutMapping("/customer/{CustomerId}")
		public ResponseEntity<Object> updateCustomer(@PathVariable(value="CustomerId") Long customerid,@Valid @RequestBody CustomerDto customerDto){
			try{				
				return customerApplicationService.performUpdateCustomer(customerDto, customerid);
			} catch(IllegalArgumentException ex) {
				return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
			} catch(Exception ex) {
				return this.responseHandler.getAppExceptionResponse();
			}	
		}
		
		//EliminarIdCustomer
		@DeleteMapping("/customer/{CustomerId}")
		public ResponseEntity<Object> deleteCustomer(@PathVariable(value="CustomerId") int customerid){
		try{
				return customerApplicationService.performDelCustomerId(customerid);					
		} catch(IllegalArgumentException ex) {
			return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
		} catch(Exception ex) {
			return this.responseHandler.getAppExceptionResponse();
		}						
		}
		
		//ListaCustomer
	    @CrossOrigin(origins = "http://localhost:4200")	
	    @GetMapping("/customer")
		public List<Customer> getAllCustomer() throws Exception{
		   return customerApplicationService.performCustomergetAll();
		}
	}