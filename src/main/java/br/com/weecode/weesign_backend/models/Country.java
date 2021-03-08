package br.com.weecode.weesign_backend.models;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;

@Entity
@Data
public class Country implements Serializable {
	
	private static final long serialVersionUID = -6842652091597643136L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String country_code;
	private String flag;
	@CreatedDate
	private Instant created_at;
	@LastModifiedDate
	private Instant updated_at;
}
