package br.com.weecode.weesign_backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.weecode.weesign_backend.models.City;
import br.com.weecode.weesign_backend.repository.CityRepository;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("city")
@ApiIgnore
public class CityController {
	
	private CityRepository repository;
	
	public CityController(CityRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping("/all")
	public List<City> getAllCities() {
		return repository.findAll();
	}
	
	@DeleteMapping("/{id}")
	public void deleteCity(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
