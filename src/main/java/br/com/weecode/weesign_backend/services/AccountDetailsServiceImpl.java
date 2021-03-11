package br.com.weecode.weesign_backend.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.weecode.weesign_backend.models.Account;
import br.com.weecode.weesign_backend.repository.AccountRepository;

import static java.util.Collections.emptyList;

@Service
public class AccountDetailsServiceImpl implements UserDetailsService {
	
	private AccountRepository repository;
	
	public AccountDetailsServiceImpl(AccountRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = this.repository.findByUsername(username);
		if(account == null) {
			throw new UsernameNotFoundException(username);
		}
		
		return new User(account.getUsername(), account.getPassword(), emptyList());
	}
	
	
	public boolean verifyEmailPerUser(String email, boolean reset) {
		
		Account verify_account = this.repository.findByEmail(email);
		if(reset) 
			this.sendLinkToNewPassword(email);
		
		return verify_account != null;
	}

	private void sendLinkToNewPassword(String email) {
		// TODO enviar link para reset de senha (Verificar template)
	}
	
	

}
