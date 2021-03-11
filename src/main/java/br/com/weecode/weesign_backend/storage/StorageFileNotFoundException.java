package br.com.weecode.weesign_backend.storage;

public class StorageFileNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 5322606327535381569L;
	
	public StorageFileNotFoundException(String message) {
		super(message);
	}
	
	public StorageFileNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
