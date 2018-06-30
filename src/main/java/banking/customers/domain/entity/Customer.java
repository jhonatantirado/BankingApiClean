package banking.customers.domain.entity;

import java.util.Date;
import java.util.Set;

import banking.accounts.domain.entity.BankAccount;

public class Customer {
	
	    private String email;
        private String cellphone;
        private int active;
        private String documentNumber;
        private Date birthDate;
	    private String lastName;
	    private String firstName;
	    private long id;
	    public Date getBirthDate() {
			return birthDate;
		}

		public void setBirthDate(Date birthDate) {
			this.birthDate = birthDate;
		}

		public String getDocumentNumber() {
			return documentNumber;
		}

		public void setDocumentNumber(String documentNumber) {
			this.documentNumber = documentNumber;
		}

		public String getCellphone() {
			return cellphone;
		}

		public void setCellphone(String cellphone) {
			this.cellphone = cellphone;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		
    
   
    public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	private Set<BankAccount> bankAccounts;

    public Customer() {
    }

    public String getFullName() {
        return String.format("%s, %s", this.lastName, this.firstName);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(Set<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }
}
