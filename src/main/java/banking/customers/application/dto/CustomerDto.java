package banking.customers.application.dto;


import banking.common.application.dto.RequestBaseDto;

public class CustomerDto  extends RequestBaseDto {
	private String firstName;
	private String lastName;
	
	private int id;
	
			
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
