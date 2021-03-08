package br.com.weecode.weesign_backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.weecode.weesign_backend.models.AccountSetting;
import br.com.weecode.weesign_backend.repository.AccountSettingRepository;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("account/setting")
@Api(tags="Configurações de contas e usuários")
public class AccountSettingController {
	
	private AccountSettingRepository repository;
	
	public AccountSettingController(AccountSettingRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping("/all")
	public List<AccountSetting> getAllAccountSettings() {
		return repository.findAll();
	}
	
	@DeleteMapping("/{id}")
	public void deleteAccountSetting(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
