package br.com.weecode.weesign_backend.exceptions;

public class DocumentNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3649109530229933011L;

	public DocumentNotFoundException(Long id) {
		super("Não foi possível encontrar o documento " + id);
	}
}
