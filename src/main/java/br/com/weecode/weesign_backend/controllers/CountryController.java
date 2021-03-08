package br.com.weecode.weesign_backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.weecode.weesign_backend.models.Country;
import br.com.weecode.weesign_backend.repository.CountryRepository;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("country")
@ApiIgnore
public class CountryController {
	
	private CountryRepository repository;
	
	public CountryController(CountryRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping("/all")
	public List<Country> getAllCountries() {
		return repository.findAll();
	}
	
	@DeleteMapping("/{id}")
	public void deleteCountry(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
