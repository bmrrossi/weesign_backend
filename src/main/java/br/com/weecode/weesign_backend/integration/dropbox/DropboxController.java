package br.com.weecode.weesign_backend.integration.dropbox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dropbox.core.v2.DbxClientV2;

import br.com.weecode.weesign_backend.integration.dropbox.DropboxUtil.Response;

@RestController
@RequestMapping("dropbox")
public class DropboxController {
	
	@Autowired
	DropboxServiceImpl dropboxService;

	@Autowired
	DbxClientV2 dropboxClient;

	@PostMapping("/upload")
	public String handleFileUplad(@RequestParam("file") MultipartFile file, @RequestParam("filePath") String filePath) throws Exception {
		dropboxService.uploadFile(file, filePath);
		return "You successfully uploaded " + filePath + "!!!";
	}

	@GetMapping("/list")
	public List<Map<String, Object>> index(@RequestParam(value = "target", required = false, defaultValue = "") String target) throws Exception {
		return dropboxService.getFileList(target);
	}
	
	@GetMapping("/browse")
	public Map<String, Object> browse(@RequestParam(value = "target", required = false, defaultValue = "") String target) throws Exception {
		Map<String, Object> data = new HashMap<>();
		data.put("data", dropboxService.getDropboxItems(target));

		return data;
	}

	@GetMapping("/download")
	public void downloadFile(HttpServletResponse response, @RequestBody DropboxUtil.Download download) throws Exception {
		dropboxService.downloadFile(response, download);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Response> deleteFile(@RequestBody DropboxUtil.Delete delete, BindingResult result) throws Exception {
		dropboxService.deleteFile(delete);

		DropboxUtil.Response response = new DropboxUtil.Response(200, "success");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
