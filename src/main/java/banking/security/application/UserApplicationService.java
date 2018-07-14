package banking.security.application;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.crypto.spec.SecretKeySpec;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import banking.common.api.controller.Utilidades;
import banking.common.application.Notification;
import banking.common.application.enumeration.RequestBodyType;
import banking.customers.application.dao.CustomerDAO;
import banking.customers.application.dto.CustomerDto;

import banking.customers.domain.repository.CustomerRepositoryN;
import banking.security.application.dto.CredentialInputDto;
import banking.security.application.dto.JwTokenOutputDto;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Service()
public class UserApplicationService {
	
	public static final String JWt_ENCODED_KEY = "k8zgjphoSZl4aTtNKiOXMQ==";

	@Autowired
	CustomerRepositoryN customerRepositoryN;
	
	@Autowired
	CustomerDAO customerDAO;
	String user,first_name,last_name,document_number,cellphone,email,detalle;
	int customer_id;
	Date birth_date;
	Boolean isactive;
	@Transactional
	public JwTokenOutputDto verifyUser(CredentialInputDto dto) {
		
		Notification notification = this.validation(dto);
		if (notification.hasErrors()) {
			throw new IllegalArgumentException(notification.errorMessage());
		}
		
		String username = dto.getUserName();
		String password = dto.getPassword();
		
		
		return validateUser(username, password);
	} 
	
	@Transactional
	public JwTokenOutputDto verifyUser(String username, String password) {
		
		Notification notification = this.validation(username, password);
		if (notification.hasErrors()) {
		throw new IllegalArgumentException(notification.errorMessage());
		}
		
		return validateUser(username, password);
	}

	private JwTokenOutputDto validateUser(String username, String password) {
		 String EnriptarClave = Utilidades.Encriptar(password);
		List<CustomerDto> customerDto =  customerDAO.findByUserName(username,EnriptarClave);
		String accessToken = null;
		if (customerDto.size() > 0)
		{
			for (CustomerDto customer : customerDto) {			
				 customer_id = customer.getId();
				 first_name= customer.getFirstName();			 
				 last_name= customer.getLastName();
				 birth_date= customer.getBirthDate();
				 document_number= customer.getDocumentNumber();
				 isactive= customer.getIsactive();
				 cellphone = customer.getCellphone();
				 email= customer.getEmail();
				 detalle=customer.getId_rol();
				 user = customer.toString();
				 System.out.println(customer);
				}
						
			accessToken = this.generateJWT(JWt_ENCODED_KEY);
			System.out.println("clave generado " + accessToken);
			
			
		}
		
		
		JwTokenOutputDto AccessTokenResponse = new JwTokenOutputDto(accessToken, "jwt", 36000, accessToken);
		
	
		return AccessTokenResponse;
	} 
	public String generateJWT(String jwtEncodedKey) {

		byte[] decodedKey = Base64.getDecoder().decode(jwtEncodedKey);

		Key secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");

		
		String accessToken = Jwts.builder()
				//.setSubject(user)
				          //  .claim("message", secretKey)
				            .claim("customer_id", customer_id)
				            .claim("first_name", first_name)
				            .claim("last_name", last_name)
				            .claim("birth_date", birth_date)
				            .claim("document_number", document_number)
				            .claim("isactive", isactive)
				            .claim("birth_date", birth_date)
				            .claim("cellphone", cellphone)
				            .claim("email", email)
				            .claim("detalle", detalle)
				            .claim("userName", user)
				            .signWith(SignatureAlgorithm.HS512, secretKey)
				            .compact();			

		return accessToken;
	}
	private Notification validation(String username, String password) {
	Notification notification = new Notification();
	if (StringUtils.isEmpty(username)) {
		notification.addError("Username is empty.");
		}
		
		if (StringUtils.isEmpty(password)) {
			notification.addError("Password is empty.");
	}
		return notification;
	}

	private Notification validation(CredentialInputDto requestBankTransferDto) {
		Notification notification = new Notification();
		if (requestBankTransferDto == null || requestBankTransferDto.getRequestBodyType() == RequestBodyType.INVALID) {
			notification.addError("Invalid JSON data in request body.");
		}
		return notification;
	}

}
