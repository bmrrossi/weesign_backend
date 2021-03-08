package br.com.weecode.weesign_backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.weecode.weesign_backend.models.AccountGroup;
import br.com.weecode.weesign_backend.repository.AccountGroupRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("group")
@Api(tags="Grupos de contas de usuários")
public class AccountGroupController {
	
	private AccountGroupRepository repository;
	
	public AccountGroupController(AccountGroupRepository repository) {
		this.repository = repository;
	}
	
	@ApiOperation(value = "Get all groups on weesign")
	@GetMapping("/all")
	public List<AccountGroup> getAllAccountGroups() {
		return repository.findAll();
	}
	
	@ApiOperation(value = "Delete group by Id")
	@DeleteMapping("/{id}")
	public void deleteAccountGroup(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
