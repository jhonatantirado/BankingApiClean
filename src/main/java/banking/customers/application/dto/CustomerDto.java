package banking.customers.application.dto;


import java.util.Date;

import banking.common.application.dto.RequestBaseDto;

public class CustomerDto  extends RequestBaseDto {
	
	
	private int id;
	private String firstName;
    private String lastName;
    private Date birthDate;
    private String documentNumber;
    private int active;
    private String cellphone;
    private String email;
    private String user;
    private String password; 
    private String id_rol;	
	
    public CustomerDto() {
	}
	
    
    
	 public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	public String getId_rol() {
		return id_rol;
	}

	public void setId_rol(String id_rol) {
		this.id_rol = id_rol;
	}
	

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	 public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	
	
			
	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	
}
