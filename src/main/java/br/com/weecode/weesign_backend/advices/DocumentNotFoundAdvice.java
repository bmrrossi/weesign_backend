package br.com.weecode.weesign_backend.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.weecode.weesign_backend.exceptions.DocumentNotFoundException;

@ControllerAdvice
public class DocumentNotFoundAdvice {
	
	@ResponseBody
	@ExceptionHandler(DocumentNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String documentNotFoundHandler(DocumentNotFoundException ex) {
		return ex.getMessage();
	}
}
