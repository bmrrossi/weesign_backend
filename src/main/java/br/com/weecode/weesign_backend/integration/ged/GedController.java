package br.com.weecode.weesign_backend.integration.ged;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.weecode.weesign_backend.integration.ged.GedUtil.Response;
import br.com.weecode.weesign_backend.models.Account;

@RestController
@RequestMapping("weeenterprise")
public class GedController {

	@Autowired
	GedServiceImpl gedService;
	
	@PostMapping("/upload")
	public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("filePath") String filePath, Account account) {
		gedService.uploadFile();
		return "You successfully uploaded " + filePath + "!!!";
	}
	
	@GetMapping("/download")
	public void downloadFile(HttpServletResponse response, @RequestBody GedUtil.Download download) {
		gedService.downloadFile(response, download);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<Response> deleteFile(@RequestBody GedUtil.Delete delete) {
		gedService.deleteFile(delete);
		GedUtil.Response response = new GedUtil.Response(200, "success");
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
