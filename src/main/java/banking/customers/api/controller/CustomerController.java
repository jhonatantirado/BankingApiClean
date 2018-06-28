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
		@CrossOrigin(origins = "http://localhost:4200")
		@RequestMapping(method = RequestMethod.POST, path = "createcustomer", consumes = "application/json; charset=UTF-8", produces = "application/json; charset=UTF-8")
		public ResponseEntity<Object> performCreate(@RequestBody CustomerDto customerDto) throws Exception {
			try {
				customerApplicationService.performCreate(customerDto);
				return this.responseHandler.getOkCommandResponse("Transfer done!");
			} catch(IllegalArgumentException ex) {
				return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
			} catch(Exception ex) {
				return this.responseHandler.getAppExceptionResponse();
			}
		}
		
		@PostMapping("/createcustomer2")
		public Customer createEmployee(@Valid @RequestBody Customer customer) {
			
			    return customerApplicationService.save(customer);			   

		}
				
		@CrossOrigin(origins = "http://localhost:4200")	
		@GetMapping("/getallcustomer")
		public List<Customer> getAllCustomer() throws Exception{	
			
			return customerApplicationService.performCustomergetAll();			
		}	
		
		@CrossOrigin(origins = "http://localhost:4200")	
		@GetMapping("/customerget/{id}")
		public ResponseEntity<Object> getCustomerById(@PathVariable(value="id") Long customerid){
			
			try {
				Customer customer=customerApplicationService.findOne(customerid);
				
				if(customer==null) {
					return ResponseEntity.notFound().build();
				}
				return ResponseEntity.ok().body(customer);
			} catch(IllegalArgumentException ex) {
				return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
			} catch(Exception ex) {
				return this.responseHandler.getAppExceptionResponse();
			}
		}
		
		@PutMapping("/customerup/{id}")
		public ResponseEntity<Object> updateCustomer(@PathVariable(value="id") Long customerid,@Valid @RequestBody Customer customerDto){
			try{
			Customer customer =customerApplicationService.findOne(customerid);
			if(customer==null) {
				return ResponseEntity.notFound().build();
			}
			
			customer.setFirstName(customerDto.getFirstName());
			customer.setLastName(customerDto.getLastName());
			customer.setActive(customerDto.getActive());
			
			Customer updateEmployee=customerApplicationService.save(customer);
			return ResponseEntity.ok().body(updateEmployee);
			
			} catch(IllegalArgumentException ex) {
				return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
			} catch(Exception ex) {
				return this.responseHandler.getAppExceptionResponse();
			}	
		}
		
		
		@DeleteMapping("/customerdel/{id}")
		public ResponseEntity<Object> deleteCustomer(@PathVariable(value="id") Long empid){
			
			try{
				Customer customer=customerApplicationService.findOne(empid);
				if(customer==null) {
					return ResponseEntity.notFound().build();
				}
				customerApplicationService.delete(customer);
				
				return ResponseEntity.ok().build();	
				
		} catch(IllegalArgumentException ex) {
			return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
		} catch(Exception ex) {
			return this.responseHandler.getAppExceptionResponse();
		}		
					
		}	
	}