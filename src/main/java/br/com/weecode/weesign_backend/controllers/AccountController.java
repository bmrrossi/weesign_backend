package br.com.weecode.weesign_backend.controllers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.weecode.weesign_backend.models.Account;
import br.com.weecode.weesign_backend.repository.AccountRepository;
import br.com.weecode.weesign_backend.services.AccountDetailsServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("account")
@Api(tags="Contas e Usuários")
public class AccountController {
	
	private AccountRepository repository;
	private AccountDetailsServiceImpl service;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public AccountController(AccountRepository repository, BCryptPasswordEncoder bCryptPasswordEncoder, AccountDetailsServiceImpl service) {
		this.service = service;
		this.repository = repository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@ApiOperation(value = "Sign up! Be a Weesigner")
	@PostMapping("/sign-up")
	public void signUp(@RequestBody Account account) {
		account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
		this.repository.save(account);
	}
	
	@ApiOperation(value = "Update account data by id")
	@PutMapping("{id}")
	public void editAccount(@PathVariable long id, @RequestBody Account account) {
		
	}
	
	@ApiOperation(value = "Verify email and send a link with new password worfklow")
	@PostMapping("/forgot-my-password")
	public boolean forgotMyPassword(@RequestBody String email) {
		return this.service.verifyEmailPerUser(email, true);
	}
	
	@ApiOperation(value = "Change a account password")
	@PostMapping("/change-my-password")
	public boolean changeMyPassword(@RequestBody Account account, String new_password) {
		return true;
	}
	
	@ApiOperation(value = "Verify if account is available to use weesign")
	@PostMapping("/pay-verify")
	public void payVerify() {
		
	}
}
