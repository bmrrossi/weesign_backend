package br.com.weecode.weesign_backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.weecode.weesign_backend.models.Contact;
import br.com.weecode.weesign_backend.repository.ContactRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("contact")
@Api(tags="Contatos")
public class ContactController {
	
	private ContactRepository repository;
	
	public ContactController(ContactRepository repository) {
		this.repository = repository;
	}
	
	@ApiOperation(value = "Get all contacts on weesign")
	@GetMapping("/all")
	public List<Contact> getAllContacts() {
		return repository.findAll();
	}
	
	@ApiOperation(value = "Get contact by Id")
	@DeleteMapping("/{id}")
	public void deleteContact(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
