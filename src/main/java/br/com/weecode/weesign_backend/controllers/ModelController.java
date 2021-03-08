package br.com.weecode.weesign_backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.weecode.weesign_backend.models.Model;
import br.com.weecode.weesign_backend.repository.ModelRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("model")
@Api(tags="Modelos de Documentos")
public class ModelController {
	
	private ModelRepository repository;
	
	public ModelController(ModelRepository repository) {
		this.repository = repository;
	}
	
	@ApiOperation(value = "Get all models on weesign")
	@GetMapping("/all")
	public List<Model> getAllModels() {
		return repository.findAll();
	}
	
	@ApiOperation(value = "Delete model by Id")
	@DeleteMapping("/{id}")
	public void deleteModel(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
