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
		Account account = repository.findByUsername(username);
		if(account == null) {
			throw new UsernameNotFoundException(username);
		}
		
		return new User(account.getUsername(), account.getPassword(), emptyList());
	}

}
