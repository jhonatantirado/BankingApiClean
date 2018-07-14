package banking.security.domain.entity;

import java.security.Key;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.spec.SecretKeySpec;

import org.mindrot.jbcrypt.BCrypt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class User {

	private String username;
	private String password;	

	public User() {
	}

	public User(String username, String password) {

		this.username = username;

		this.password = BCrypt.hashpw(password, BCrypt.gensalt(15));
		
	}

	public User(String username, String password, String email,

		boolean enabled, Set<UserRole> userRole) {

		this.username = username;

		this.password = BCrypt.hashpw(password, BCrypt.gensalt(15));

		
	}

	public boolean verifyIdentity(String plainPassword) {

		return BCrypt.checkpw(plainPassword, password);
	}

	public String generateJWT(String jwtEncodedKey) {

		byte[] decodedKey = Base64.getDecoder().decode(jwtEncodedKey);

		Key secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");

		
		String accessToken = Jwts.builder().setSubject(username).claim("userName", username).signWith(SignatureAlgorithm.HS512, secretKey)
				.compact();

		return accessToken;
	}

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {

		this.password = password;
	}

	
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}
	
}
