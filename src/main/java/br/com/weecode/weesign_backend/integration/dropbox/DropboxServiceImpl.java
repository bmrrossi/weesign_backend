package br.com.weecode.weesign_backend.integration.dropbox;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.Metadata;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DropboxServiceImpl {
	
	private static final Logger logger = LoggerFactory.getLogger(DropboxServiceImpl.class);

	@Autowired
	DbxClientV2 dropboxClient;
	
	public void uploadFile(MultipartFile file, String filePath) throws IOException, DbxException {
		ByteArrayInputStream inputStream = new ByteArrayInputStream(file.getBytes());
		Metadata uploadMetaData = dropboxClient.files().uploadBuilder(filePath).uploadAndFinish(inputStream);
		logger.info("uploading metadata => {}", uploadMetaData.toString());
		inputStream.close();
	}
	
	public List<Map<String, Object>> getFileList(String target) throws IOException, DbxException {
		List<Metadata> entries = dropboxClient.files().listFolder(target).getEntries();
		List<Map<String, Object>> result = new ArrayList<>();
		
		for(Metadata entry : entries) {
			if(entry instanceof FileMetadata) {
				logger.info("{} is file", entry.getName());
			}
			String metaDataString = entry.toString();
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> map = new HashMap<>();
			map = mapper.readValue(metaDataString, new TypeReference<Map<String, Object>>(){});
			result.add(map);
		}
		
		return result;
	}
	
	public List<Dropbox> getDropboxItems(String path) throws IOException, DbxException {
		List<Metadata> entries = dropboxClient.files().listFolder(path).getEntries();
		List<Dropbox> result = new ArrayList<>();
		entries.stream().forEach(entry -> {
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> map = new HashMap<>();
			try {
				map = mapper.readValue(entry.toString(), new TypeReference<Map<String, Object>>(){});
			} catch (IOException e) {
				e.printStackTrace();
			}

			Dropbox item = new Dropbox();
			item.setId(map.get("id").toString());
			item.setName(map.get("name").toString());
			item.setPath(map.get("path_lower").toString());
			result.add(item);
		});
		return result;
	}
	
	public void downloadFile(HttpServletResponse response, DropboxUtil.Download download) throws IOException, DbxException {
		response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
		response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(download.getFileName(), "UTF-8")+"\";");
		response.setHeader("Content-Transfer-Encoding", "binary");

		ServletOutputStream outputStream = response.getOutputStream();
		dropboxClient.files().downloadBuilder(download.getFilePath()).download(outputStream);

		response.getOutputStream().flush();
		response.getOutputStream().close();
	}

	public void deleteFile(DropboxUtil.Delete delete) throws DbxException {
		dropboxClient.files().delete(delete.getFilePath());
	}
	
}
