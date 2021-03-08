package br.com.weecode.weesign_backend;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;


@SpringBootApplication
@ComponentScan(basePackages = { "br.com.weecode.weesign_backend" })
public class WeesignBackendApplication {

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(WeesignBackendApplication.class, args);
	}
	
	@Bean("dropboxClient")
	public DbxClientV2 dropboxClient() throws DbxException {
		String ACCESS_TOKEN = "Your dropbox oauth2 access token";
		DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial");
		DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
		return client;
	}
	
}
