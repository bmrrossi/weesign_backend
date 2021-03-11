package br.com.weecode.weesign_backend.storage;

import java.io.File;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
	
	void init();
	
	void store(MultipartFile file);
	
	Stream<Path> loadAll();
	
	Path load(String filename);
	
	File loadAsResource(String filename);
	
	void deleteAll();
}
