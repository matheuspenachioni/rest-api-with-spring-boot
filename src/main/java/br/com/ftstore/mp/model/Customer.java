package br.com.ftstore.mp.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity @Table(name = "customer")
@Data @NoArgsConstructor @AllArgsConstructor
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_customer")
	private Long id;
	
	@Column(name = "first_name_customer", nullable = false, unique = true)
	@Length(min = 3, max = 100) @NotBlank(message = "O primeiro nome deve ser informado!")
	private String firstName;
	
	@Column(name = "last_name_customer", nullable = false, unique = true)
	@Length(min = 3, max = 100) @NotBlank(message = "O último nome deve ser informado!")
	private String lastName;
	
	@CPF(message = "O CPF informado é inválido!")
	@Column(name = "cpf_customer", nullable = false, unique = true)
	@Length(min = 11, max = 11) @NotBlank(message = "O CPF deve ser informado!")
	private String cpf;
	
	@Email(message = "O e-mail informado é inválido!")
	@Column(name = "email_customer", nullable = false, unique = true)
	@Length(min = 3, max = 300) @NotBlank(message = "O e-mail deve ser informado!")
	private String email;
	
	@Column(name = "password_customer", nullable = false, unique = true)
	@Length(min = 3, max = 500) @NotBlank(message = "A senha deve ser informada!")
	private String password;
	
	@Column(name = "status_customer", nullable = false)
	private Boolean status;

	@JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "birthdate_customer", nullable = false, updatable = false)
    private LocalDate birthdate;
	
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(name = "date_created", nullable = false, updatable = false)
	private LocalDateTime dateCreated;
    
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(name = "date_updated", nullable = false)
	private LocalDateTime dateUpdated;
	
	@PrePersist
	private void prePersist() {
		this.setDateCreated(LocalDateTime.now());
		this.setDateUpdated(LocalDateTime.now());
		this.setStatus(true);
	}
	
	@PreUpdate
	private void preUpdate() {
		this.setDateUpdated(LocalDateTime.now());
	}
	
}
