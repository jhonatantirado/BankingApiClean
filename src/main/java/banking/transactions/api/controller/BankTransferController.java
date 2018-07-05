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
import banking.transactions.application.TransactionApplicationService;
import banking.transactions.application.dto.RequestBankTransferDto;
import banking.transdetalle.application.transdetalleApplicationService;
import banking.transdetalle.domain.entity.transDetalle;

@RestController
@RequestMapping("api/")
public class BankTransferController{
	
	@Autowired
	TransactionApplicationService transactionApplicationService;
	
	@Autowired
	ResponseHandler responseHandler;
	
	@Autowired
	transdetalleApplicationService ttransdetalleApplicationService;

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
	@RequestMapping(method = RequestMethod.GET, path = "index", produces = "application/json; charset=UTF-8")
	public ResponseEntity<Object> Index() throws Exception {
		try {
			return this.responseHandler.getOkCommandResponse("Hola Mundo!!!");
		} catch(IllegalArgumentException ex) {
			return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
		} catch(Exception ex) {
			return this.responseHandler.getAppExceptionResponse();
		}
	}
	
	@CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.GET, value = "/getAccountIdCustomerTransfers/{CustomerId}")
	public List<transDetalle> getAccountIdCustomer(@PathVariable(value="CustomerId") Long customerid) throws Exception{
		return ttransdetalleApplicationService.getCustomertransDetalle(customerid);
	}
}
