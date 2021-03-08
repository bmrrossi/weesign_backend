package br.com.weecode.weesign_backend.models;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;

@Entity
@Data
public class City {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String city_code;
	private String state;
	@OneToOne
	private Country country;
	@CreatedDate
	private Instant created_at;
	@LastModifiedDate
	private Instant updated_at;
}
