package br.com.weecode.weesign_backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.weecode.weesign_backend.models.Workflow;
import br.com.weecode.weesign_backend.repository.WorkflowRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("workflow")
@Api(tags="Workflow e Processos")
public class WorkflowController {

	private WorkflowRepository repository;
	
	public WorkflowController(WorkflowRepository repository) {
		this.repository = repository;
	}
	
	@ApiOperation(value = "Get all workflows on weesign")
	@GetMapping("/all")
	public List<Workflow> getAllWorkflows() {
		return repository.findAll();
	}
	
	@ApiOperation(value = "Delete Workflow by Id")
	@DeleteMapping("/{id}")
	public void deleteWorkflow(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
}
