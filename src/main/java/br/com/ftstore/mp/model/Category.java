package br.com.ftstore.mp.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity @Table(name = "category")
@Data @NoArgsConstructor @AllArgsConstructor
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_category")
	private Long id;
	
	@Column(name = "name_category", nullable = false, unique = true)
	@Length(min = 3, max = 100) @NotBlank(message = "O nome da categoria deve ser informado!")
	private String name;
	
	@Column(name = "description_category", nullable = false)
	@Length(min = 3, max = 400) @NotBlank(message = "A descrição da categoria deve ser informada!")
	private String description;
	
}
