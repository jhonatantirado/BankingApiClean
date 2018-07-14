package banking.transactions.api.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import banking.common.api.controller.ResponseHandler;
import banking.customers.application.CustomerApplicationService;
import banking.customers.domain.entity.Customer;
import banking.transactions.application.TransactionApplicationService;
import banking.transactions.application.dto.RequestBankTransferDto;
import banking.transdetalle.application.transdetalleApplicationService;
import banking.transdetalle.domain.entity.transDetalle;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import banking.security.application.UserApplicationService;
import banking.security.application.dto.JwTokenOutputDto;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RestController
@RequestMapping("api/")
public class BankTransferController{
	
	@Autowired
	TransactionApplicationService transactionApplicationService;
	
	@Autowired
	ResponseHandler responseHandler;
	
	@Autowired	
	UserApplicationService userApplicationService;
	
	@Autowired
	transdetalleApplicationService ttransdetalleApplicationService;	
	
	@Autowired
	CustomerApplicationService customerApplicationService;
	
	@CrossOrigin(origins = "*")
	@RequestMapping(method = RequestMethod.POST, path = "transfers", consumes = "application/json; charset=UTF-8", produces = "application/json; charset=UTF-8")
	public ResponseEntity<Object> performTransfer(@RequestBody RequestBankTransferDto requestBankTransferDto) throws Exception {
		try {
			transactionApplicationService.performTransfer(requestBankTransferDto);
			return this.responseHandler.getOkCommandResponse("Transfer done!");
		} catch(IllegalArgumentException ex) {
			return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
		} catch(Exception ex) {
			return this.responseHandler.getAppExceptionResponse();
		}
	}		
	
	@CrossOrigin(origins = "*")		  
    @RequestMapping(method = RequestMethod.GET, value = "/login")	
	public List<Customer> getAllCustomer(String user, String password) throws Exception{
		try {
			JwTokenOutputDto response = userApplicationService.verifyUser(user, password);
			System.out.println("codigo ---> " + response.getAccessToken());
			if (response.getAccessToken().equals(null)) {				 
				JsonObject jsonObject1 = Json.createObjectBuilder()
				                .add("Mensaje", "Datos Incorrectos")				                
				                 .build();				
				return (List<Customer>) Response.ok(jsonObject1).build();
			}
			return customerApplicationService.getLoginCustomer(response.getAccessToken() ,user, password);			
		} catch(Exception ex) {
			String error = ex.toString();			
		}
		return null;	
	}
	
	@CrossOrigin(origins = "*")		  
    @RequestMapping(method = RequestMethod.GET, value = "/login2")
	public Response validar(String user, String password) throws Exception{	
		
			JwTokenOutputDto response = userApplicationService.verifyUser(user, password);
			System.out.println("codigo ---> " + response.getAccessToken());
			if (response.getAccessToken().equals(null)) {		 
				JsonObject json = Json.createObjectBuilder()
				                .add("Mensaje", "Datos Incorrectos")				                
				        .build();				    
			return	Response.ok(json).build();
			}
			
		return (Response) customerApplicationService.getLoginCustomer(response.getAccessToken() ,user, password);			
	}
	
	@CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.GET, value = "/transfers/{CustomerId}")
	public List<transDetalle> getAccountIdCustomer(@PathVariable(value="CustomerId") Long customerid) throws Exception{
		return ttransdetalleApplicationService.getCustomertransDetalle(customerid);
	}
}
