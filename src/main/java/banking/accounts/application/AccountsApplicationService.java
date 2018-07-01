package banking.accounts.application;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import banking.accounts.application.dto.BankAccountDto;
import banking.accounts.domain.entity.BankAccount;
import banking.accounts.domain.repository.BankAccountRepositoryN;
import banking.common.application.Notification;
import banking.common.application.enumeration.RequestBodyType;
import banking.customers.application.dto.CustomerDto;

@Service()
public class AccountsApplicationService {

	@Autowired
	BankAccountRepositoryN bankAccountRepositoryN;
	
	@Transactional
	public ResponseEntity<Object> performCreateAccount(BankAccountDto bankAccountDto,CustomerDto customerDto) throws Exception {		
		Notification notification = this.validation(bankAccountDto);
        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }
        BankAccount bankAccount = new BankAccount(); 
    
		bankAccount.setNumber(bankAccountDto.getNumber());
		bankAccount.setBalance(bankAccountDto.getBalance());
		bankAccount.setIsLocked(true);
		//bankAccount.setCustomer(customerDto.getId());
		BankAccount CreateBankAccountr= this.save(bankAccount);		
		return ResponseEntity.ok().body(CreateBankAccountr);				
	}	
	
	@Transactional
	public ResponseEntity<Object> performUpdateAccounts(BankAccountDto bankAccountDto, Long accountid) throws Exception {
		Notification notification = this.validation(bankAccountDto);
        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }
        BankAccount bankAccount = this.findOne(accountid);
		if(bankAccount==null) {
			return ResponseEntity.notFound().build();
		}		
		bankAccount.setNumber(bankAccountDto.getNumber());
		bankAccount.setBalance(bankAccountDto.getBalance());
		bankAccount.setIsLocked(true);
		//bankAccount.setCustomer(customerDto.getId());
		BankAccount updateBankAccount= this.save(bankAccount);		
		return ResponseEntity.ok().body(updateBankAccount);
		
	}	
	
	@Transactional
	public ResponseEntity<Object> performGetAccountsId(int accountid) throws Exception {
		BankAccountDto bankAccountDto = new BankAccountDto();
		bankAccountDto.setId(accountid);		
		Notification notification = this.validation(bankAccountDto);
        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }		
        BankAccount bankAccount=this.findOne(Long.valueOf(accountid));		
		if(bankAccount==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(bankAccount);
	}
	
	
	@Transactional
	public ResponseEntity<Object> performDelAccountsId(int accountid) throws Exception {
		BankAccountDto bankAccountDto = new BankAccountDto();
		bankAccountDto.setId(accountid);		
		Notification notification = this.validation(bankAccountDto);
        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }		
        BankAccount bankAccount=this.findOne(Long.valueOf(accountid));
		if(bankAccount==null) {
			return ResponseEntity.notFound().build();
		}
		this.delete(bankAccount);		
		return ResponseEntity.ok().build();	
	}
	
	
	@Transactional
	public List<BankAccount> performAccountGetAll()  throws Exception {
		return bankAccountRepositoryN.findAll();
	}
	
	
	@Transactional
	public BankAccount save(BankAccount bankAccount) {
		return bankAccountRepositoryN.save(bankAccount);
	}
	
	@Transactional
	public void delete(BankAccount bankAccount) {
		bankAccountRepositoryN.delete(bankAccount);
	}
	
	@Transactional
	public BankAccount findOne(Long empid) {
		return bankAccountRepositoryN.findOne(empid);
	}
	
	private Notification validation(BankAccountDto bankAccountDto) {
		Notification notification = new Notification();
		if (bankAccountDto == null || bankAccountDto.getRequestBodyType() == RequestBodyType.INVALID) {
			notification.addError("Invalid JSON data in request body.");
		}
		return notification;
	}
}
