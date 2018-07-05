package banking.transactions.application;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import banking.accounts.domain.entity.BankAccount;
import banking.accounts.domain.repository.BankAccountRepository;
import banking.common.application.Notification;
import banking.common.application.enumeration.RequestBodyType;
import banking.transactions.application.dto.RequestBankTransferDto;
import banking.transactions.domain.service.TransferDomainService;
import banking.transdetalle.application.transdetalleApplicationService;
import banking.transdetalle.application.dto.transDetalleDto;
import banking.transdetalle.domain.entity.transDetalle;

@Service()
public class TransactionApplicationService {
	@Autowired
	private BankAccountRepository bankAccountRepository;

	@Autowired
	private TransferDomainService transferDomainService;

     @Autowired
     private transdetalleApplicationService ttransdetalleApplicationService;

	@Transactional
	public void performTransfer(RequestBankTransferDto requestBankTransferDto) throws Exception {
		Notification notification = this.validation(requestBankTransferDto);
        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }
		BankAccount originAccount = this.bankAccountRepository.findByNumberLocked(requestBankTransferDto.getFromAccountNumber());
		BankAccount destinationAccount = this.bankAccountRepository.findByNumberLocked(requestBankTransferDto.getToAccountNumber());
		this.transferDomainService.performTransfer(originAccount, destinationAccount, requestBankTransferDto.getAmount());
		this.bankAccountRepository.save(originAccount);
		this.bankAccountRepository.save(destinationAccount);		
		BigDecimal unCentavo = new BigDecimal("0.01");
		transDetalle ttransDetalle = new transDetalle();		
		ttransDetalle.setNumb_origen(originAccount.getNumber());
	    ttransDetalle.setNumb_destino(destinationAccount.getNumber());
	    ttransDetalle.setBalance(requestBankTransferDto.getAmount());
	    ttransDetalle.setIslocked(false);
	    ttransDetalle.setBank_account_id(originAccount.getId());
	    ttransDetalle.setCustomer_id(originAccount.getCustomer_id());	   
		ttransdetalleApplicationService.saves(ttransDetalle);		
	}
	
	private Notification validation(RequestBankTransferDto requestBankTransferDto) {
		Notification notification = new Notification();
		if (requestBankTransferDto == null || requestBankTransferDto.getRequestBodyType() == RequestBodyType.INVALID) {
			notification.addError("Invalid JSON data in request body.");
		}
		return notification;
	}
}
