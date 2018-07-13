package banking.accounts.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import banking.accounts.application.AccountsApplicationService;
import banking.accounts.application.dto.BankAccountDto;
import banking.accounts.domain.entity.BankAccount;
import banking.common.api.controller.ResponseHandler;
import banking.customers.application.dto.CustomerDto;
import banking.customers.domain.entity.Customer;
@RestController
@RequestMapping("api/Accounts/")
public class AccountsController {
	
	@Autowired
	AccountsApplicationService accountsApplicationService;

	@Autowired
	ResponseHandler responseHandler;	
		     			
			@CrossOrigin(origins = "*")
			@RequestMapping(method = RequestMethod.POST, path = "bankAccount", consumes = "application/json; charset=UTF-8", produces = "application/json; charset=UTF-8")
			public ResponseEntity<Object> createaccount(@Valid @RequestBody BankAccountDto bankAccountDto) throws Exception {
				try {
				return accountsApplicationService.performCreateAccount(bankAccountDto);  			
				} catch(IllegalArgumentException ex) {
					return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
				} catch(Exception ex) {
					return this.responseHandler.getAppExceptionResponse();
				}
			}	
	
			@CrossOrigin(origins = "*")				
			@RequestMapping(method = RequestMethod.GET, value = "/bankAccount/{AccountsId}")
			public ResponseEntity<Object> getidaccount(@PathVariable(value="AccountsId") int accountid){
			try {				
					return accountsApplicationService.performGetAccountsId(accountid);				
				} catch(IllegalArgumentException ex) {
					return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
				} catch(Exception ex) {
					return this.responseHandler.getAppExceptionResponse();
				}
			}	
			
			@CrossOrigin(origins = "*")
			@RequestMapping(method = RequestMethod.DELETE, value = "/bankAccount/{AccountsId}")
			public ResponseEntity<Object> deletedaccount(@PathVariable(value="AccountsId") int accountid){
			try{
					return accountsApplicationService.performDelAccountsId(accountid);					
			} catch(IllegalArgumentException ex) {
				return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
			} catch(Exception ex) {
				return this.responseHandler.getAppExceptionResponse();
			}						
			}
				
			@CrossOrigin(origins = "*")
			@RequestMapping(method = RequestMethod.PUT, value = "/bankAccount/{AccountsId}")
			public ResponseEntity<Object> updateaccount(@PathVariable(value="AccountsId") Long accountid,@Valid @RequestBody BankAccountDto bankAccountDto){
				try{				
					return accountsApplicationService.performUpdateAccounts(bankAccountDto, accountid);
				} catch(IllegalArgumentException ex) {
					return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
				} catch(Exception ex) {
					return this.responseHandler.getAppExceptionResponse();
				}	
			}
			
			@CrossOrigin(origins = "*")   		   
		    @RequestMapping(method = RequestMethod.GET, value = "/bankAccount")
			public List<BankAccount> getallaccount(int offset, int limit) throws Exception{
			   return accountsApplicationService.getallAccount(offset, limit);
			}
			
			@CrossOrigin(origins = "*")   		   
		    @RequestMapping(method = RequestMethod.GET, value = "/findByAccountNumber")
			public List<BankAccount> findByAccountNumber(String accountNumber) throws Exception{
			   return accountsApplicationService.getAccountNroCuenta(accountNumber);
			}
			
			@CrossOrigin(origins = "*")
		    @RequestMapping(method = RequestMethod.GET, value = "/customer/{CustomerId}")
			public List<BankAccount> getidcustomer(@PathVariable(value="CustomerId") Long customerid) throws Exception{
			   return accountsApplicationService.getAccountIdCustomer(customerid);
			}
	
}
