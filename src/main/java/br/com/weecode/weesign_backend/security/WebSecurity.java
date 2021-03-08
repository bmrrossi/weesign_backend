package br.com.weecode.weesign_backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.weecode.weesign_backend.services.AccountDetailsServiceImpl;

import static br.com.weecode.weesign_backend.security.SecurityConstants.SIGN_UP_URL;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	private AccountDetailsServiceImpl accountDetailsService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public WebSecurity(AccountDetailsServiceImpl accountDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.accountDetailsService = accountDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests()
			.antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
			.antMatchers(publicEndpoints()).permitAll()
			.anyRequest().authenticated()
			.and()
            .oauth2Login()
			.and()
			.addFilter(new JWTAuthenticationFilter(authenticationManager()))
			.addFilter(new JWTAuthorizationFilter(authenticationManager()))
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(accountDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}
	
	private String[] publicEndpoints() {
		return new String[] {
				"/v3/api-docs", "/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**", "swagger-ui.html", "/error/**",
				"h2-console/**", "/resources/**", "/v3/rest-docs", "swagger-resources/configuration", "/swagger-resources",
				"/**.html", "/account/login", "/csrf"
		};
	}
}
