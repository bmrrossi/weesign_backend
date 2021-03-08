package br.com.weecode.weesign_backend.security;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.weecode.weesign_backend.models.Account;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static br.com.weecode.weesign_backend.security.SecurityConstants.EXPIRATION_TIME;
import static br.com.weecode.weesign_backend.security.SecurityConstants.SECRET;
import static br.com.weecode.weesign_backend.security.SecurityConstants.HEADER_STRING;
import static br.com.weecode.weesign_backend.security.SecurityConstants.TOKEN_PREFIX;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
		try {
			Account account = new ObjectMapper()
						.readValue(req.getInputStream(), Account.class);
			
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
															account.getUsername(),
															account.getPassword(),
															new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException {
		
		String token = JWT.create()
					.withSubject(((User) auth.getPrincipal()).getUsername())
					.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
					.sign(HMAC512(SECRET.getBytes()));
		
		res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
	}
	
}
