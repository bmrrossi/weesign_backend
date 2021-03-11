package br.com.weecode.weesign_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.weecode.weesign_backend.models.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

	Account findByUsername(String username);
	
	Account findByEmail(String email);
	
}
