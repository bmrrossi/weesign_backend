package br.com.weecode.weesign_backend.storage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class FileSystemStorageService implements StorageService {

	private final Path rootLocation;

	@Autowired
	public FileSystemStorageService(StorageProperties properties) {
		this.rootLocation = Paths.get(properties.getLocation());
	}

	@Override
	public void store(MultipartFile file) {
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if (file.isEmpty()) {
				log.error("Não é possivel armazenar arquivos vazios.");
				throw new StorageException("Failed to store empty file " + filename);
			}
			if (filename.contains("..")) {
				// This is a security check
				log.error("Não é possível armazenar o arquivo com caminho relativo fora do diretório atual "+filename+" .");
				throw new StorageException(
						"Cannot store file with relative path outside current directory "
								+ filename);
			}
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, this.rootLocation.resolve(filename),
					StandardCopyOption.REPLACE_EXISTING);
			}
		}
		catch (IOException e) {
			log.error("Falha ao armazenar arquivo "+filename+" .");
			throw new StorageException("Failed to store file " + filename, e);
		}
	}

	@Override
	public Stream<Path> loadAll() {
		try {
			log.info("Buscando arquivos armazenados...");
			return Files.walk(this.rootLocation, 1)
				.filter(path -> !path.equals(this.rootLocation))
				.map(this.rootLocation::relativize);
		}
		catch (IOException e) {
			log.error("Falha ao ler arquivos armazenados.");
			throw new StorageException("Failed to read stored files", e);
		}
	}

	@Override
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}

	@Override
	public File loadAsResource(String filename) {
		try {
			log.info("Lendo arquivo "+filename);
			File file = new File(filename);
			Resource resource = new UrlResource(file.toURI());
			if (resource.exists() || resource.isReadable()) {
				return file;
			}
			else {
				log.error("Não foi possivel ler o arquivo "+ filename);
				throw new StorageFileNotFoundException(
						"Could not read file: " + filename);

			}
		}
		catch (MalformedURLException e) {
			log.error("Não foi possivel ler o arquivo "+ filename);
			throw new StorageFileNotFoundException("Could not read file: " + filename, e);
		}
	}

	@Override
	public void deleteAll() {
		log.info("Removendo arquivos armazenados!");
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	@Override
	public void init() {
		try {
			log.info("Alocando espaço para armazenagem dos arquivos.");
			Files.createDirectories(rootLocation);
		}
		catch (IOException e) {
			log.error("Não foi possivel inicializar a armazenagem!");
			throw new StorageException("Could not initialize storage", e);
		}
	}
}
