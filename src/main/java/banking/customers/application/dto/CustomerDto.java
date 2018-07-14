package banking.customers.application.dto;


import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Set;

import javax.crypto.spec.SecretKeySpec;

import org.mindrot.jbcrypt.BCrypt;

import banking.common.application.dto.RequestBaseDto;
import banking.security.domain.entity.UserRole;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class CustomerDto  extends RequestBaseDto {
	
	
	private int id;
	private String firstName;
    private String lastName;
    private Date birthDate;
    private String documentNumber;
    private Boolean isactive; 
	private String cellphone;
    private String email;
    private String user;
    private String password; 
    private String id_rol;	
	  
    
    public CustomerDto() {
	}

	public CustomerDto(String user, String password) {

		this.user = user;

		this.password = BCrypt.hashpw(password, BCrypt.gensalt(15));
		
	}
	
	public String generateJWT(String jwtEncodedKey) {

		byte[] decodedKey = Base64.getDecoder().decode(jwtEncodedKey);

		Key secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");

		
		String accessToken = Jwts.builder().setSubject(user).claim("userName", user).signWith(SignatureAlgorithm.HS512, secretKey)
				.compact();

		return accessToken;
	}
	
	@Override
	public String toString() {
		return "User [username=" + user + ", password=" + password + "]";
	}
	
	public CustomerDto(String user, String password, String email,

			boolean enabled, Set<UserRole> userRole) {

			this.user = user;

			this.password = BCrypt.hashpw(password, BCrypt.gensalt(15));

			
		}

		public boolean verifyIdentity(String plainPassword) {

			return BCrypt.checkpw(plainPassword, password);
		}
    
    
    
    public Boolean getIsactive() {
		return isactive;
	}

	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
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
