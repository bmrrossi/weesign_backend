package br.com.weecode.weesign_backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.weecode.weesign_backend.exceptions.DocumentNotFoundException;
import br.com.weecode.weesign_backend.models.Document;
import br.com.weecode.weesign_backend.repository.DocumentRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("document")
@Api(tags="Documentos")
public class DocumentController {

	private DocumentRepository repository;
	
	public DocumentController(DocumentRepository repository) {
		this.repository = repository;
	}
	
	@ApiOperation(value = "Find all documents at system")
	@GetMapping("/all")
	public List<Document> getAllDocuments() {
		return repository.findAll();
	}
	
	@ApiOperation(value = "Find document by Id")
	@GetMapping("/{id}")
	public Document getDocument(@PathVariable Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new DocumentNotFoundException(id));
	}
	
	@ApiOperation(value = "Delete document by Id")
	@DeleteMapping("/{id}")
	public void deleteDocument(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
	@ApiOperation(value = "Edit document registry")
	@PutMapping("/{id}")
	public void editDocument(@PathVariable long id, @RequestBody Document document) {
		
	}
	
}
