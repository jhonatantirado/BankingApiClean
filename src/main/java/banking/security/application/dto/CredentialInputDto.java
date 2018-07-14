package banking.security.application.dto;

import banking.common.application.dto.RequestBaseDto;
import banking.common.application.enumeration.RequestBodyType;

public class CredentialInputDto extends RequestBaseDto {
	
	private String userName;
	private String password;
	
	
	
	public CredentialInputDto(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	
	
	public CredentialInputDto(RequestBodyType requestBodyType) {
		this.requestBodyType = requestBodyType;
	}


	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}
	
	

}