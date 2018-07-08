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
import org.springframework.web.servlet.ModelAndView;

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
				
					
		@CrossOrigin(origins = "*")
		@RequestMapping(method = RequestMethod.POST, path = "customer", consumes = "application/json; charset=UTF-8", produces = "application/json; charset=UTF-8")
		public ResponseEntity<Object> createcustomer(@Valid @RequestBody CustomerDto customerDto) throws Exception {
			try {
			return customerApplicationService.performCreateCustomer(customerDto);  			
			} catch(IllegalArgumentException ex) {
				return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
			} catch(Exception ex) {
				return this.responseHandler.getAppExceptionResponse();
			}
		}		
		
		
		@CrossOrigin(origins = "*")			
		@RequestMapping(method = RequestMethod.GET, value = "/customer/{CustomerId}")
		public ResponseEntity<Object> getidcustomer(@PathVariable(value="CustomerId") int customerid){
		try {				
				return customerApplicationService.performGetCustomerId(customerid);				
			} catch(IllegalArgumentException ex) {
				return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
			} catch(Exception ex) {
				return this.responseHandler.getAppExceptionResponse();
			}
		}
		
		@CrossOrigin(origins = "*")
		@RequestMapping(method = RequestMethod.PUT, value = "/customer/{CustomerId}")
		public ResponseEntity<Object> updatecustomer(@PathVariable(value="CustomerId") Long customerid,@Valid @RequestBody CustomerDto customerDto){
			try{				
				return customerApplicationService.performUpdateCustomer(customerDto, customerid);
			} catch(IllegalArgumentException ex) {
				return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
			} catch(Exception ex) {
				return this.responseHandler.getAppExceptionResponse();
			}	
		}
		
		@CrossOrigin(origins = "*")
		@RequestMapping(method = RequestMethod.DELETE, value = "/customer/{CustomerId}")
		public ResponseEntity<Object> deletedcustomer(@PathVariable(value="CustomerId") int customerid){
		try{
				return customerApplicationService.performDelCustomerId(customerid);					
		} catch(IllegalArgumentException ex) {
			return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
		} catch(Exception ex) {
			return this.responseHandler.getAppExceptionResponse();
		}						
		}
		
		@CrossOrigin(origins = "*")	
		@RequestMapping(method = RequestMethod.GET, value = "/login/{user}/{password}")
	    public List<Customer> getcustomerlogin(@PathVariable(value="user") String user,@PathVariable(value="password") String password){  
	        return customerApplicationService.getLoginCustomer(user,password);  
	    }
				
		@CrossOrigin(origins = "*")		  
	    @RequestMapping(method = RequestMethod.GET, value = "/customer")
		public List<Customer> getAllCustomer() throws Exception{
		   return customerApplicationService.performCustomergetAll();
		}
	}