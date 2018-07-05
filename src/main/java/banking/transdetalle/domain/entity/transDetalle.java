package banking.transdetalle.domain.entity;


import java.math.BigDecimal;
import java.util.Date;

public class transDetalle {
	private long id;
	private String numb_origen;
    private String numb_destino;
    private BigDecimal balance;
    
	
	private Boolean islocked;  
	private long bank_account_id;
	private long customer_id;
	

	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	    public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getNumb_origen() {
			return numb_origen;
		}
		public void setNumb_origen(String numb_origen) {
			this.numb_origen = numb_origen;
		}
		public String getNumb_destino() {
			return numb_destino;
		}
		public void setNumb_destino(String numb_destino) {
			this.numb_destino = numb_destino;
		}
		
		public Boolean getIslocked() {
			return islocked;
		}
		public void setIslocked(Boolean islocked) {
			this.islocked = islocked;
		}
		public long getBank_account_id() {
			return bank_account_id;
		}
		public void setBank_account_id(long bank_account_id) {
			this.bank_account_id = bank_account_id;
		}
		public long getCustomer_id() {
			return customer_id;
		}
		public void setCustomer_id(long customer_id) {
			this.customer_id = customer_id;
		}
		
}
