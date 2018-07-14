package banking.transactions.api.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import banking.security.application.UserApplicationService;
import banking.security.application.dto.JwTokenOutputDto;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import java.io.IOException;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.MediaType;

@Provider
@PreMatching
public class Intercepter implements ContainerRequestFilter{

	@Override	
	public void filter(ContainerRequestContext request) throws IOException {		
		
		String url = request.getUriInfo().getAbsolutePath().toString();
		if (url.contains("/api/login")){
		return;
		}
		String token =request.getHeaderString("Authorizateh");
		if (!token.equals("")){
		JsonObject json = Json.createObjectBuilder()
		                      .add("mensaje","credencialess incorrectas")
							  .build();
           request.abortWith(Response.status(Response.Status.UNAUTHORIZED)
        		                      .entity(json)
        		                      .build());
	}
		
		
	}	
}
