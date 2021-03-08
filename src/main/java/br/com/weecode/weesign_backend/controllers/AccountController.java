package br.com.weecode.weesign_backend.controllers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.weecode.weesign_backend.models.Account;
import br.com.weecode.weesign_backend.repository.AccountRepository;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("account")
@Api(tags="Contas e Usuários")
public class AccountController {
	
	private AccountRepository repository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public AccountController(AccountRepository repository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.repository = repository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@PostMapping("/sign-up")
	public void signUp(@RequestBody Account account) {
		account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
		repository.save(account);
	}
	
}
