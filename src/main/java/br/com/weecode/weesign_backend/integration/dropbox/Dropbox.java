package br.com.weecode.weesign_backend.integration.dropbox;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;

@Data
public class Dropbox implements Serializable {

	private static final long serialVersionUID = -5143575772539530207L;
	
	private String id;
	private boolean folder;
	private String name;
	private String revision;
	private String path;
	private Long size;
	private Date modifyDate;
	

}
