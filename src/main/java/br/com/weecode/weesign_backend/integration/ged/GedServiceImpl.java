package br.com.weecode.weesign_backend.integration.ged;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.weecode.weesign_backend.integration.ged.GedUtil.Delete;

@Service
public class GedServiceImpl {
	
	@Value("${weeenterprise.client.cookie}")
	private static String GED_COOKIE;
	
	@Value("${weeenterprise.client.id_area_weesign}")
	private static String GED_ID_AREA;

	public void uploadFile() {
		
	}

	public void deleteFile(Delete delete) {
		
	}

	public void downloadFile(HttpServletResponse response, GedUtil.Download download) {
		
	}	
	
}
