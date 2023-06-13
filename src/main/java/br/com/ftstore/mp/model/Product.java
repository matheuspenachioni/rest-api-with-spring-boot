package br.com.ftstore.mp.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity @Table(name = "product")
@Data @NoArgsConstructor @AllArgsConstructor
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_product")
	private Long id;

	@Column(name = "name_product", nullable = false)
	@Length(min = 3, max = 100) @NotBlank(message = "O nome do produto deve ser informado!")
	private String name;
	
	@Column(name = "price_product", nullable = false, precision = 10, scale = 2)
	private BigDecimal price;
	
	@Column(name = "amount_product", nullable = false)
	private Integer amount;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="product_category",joinColumns = {
			@JoinColumn(name="product_id", referencedColumnName = "id_product")}
	,inverseJoinColumns = {@JoinColumn(name="category_id",referencedColumnName = "id_category")})
	private Set<Category> categories = new HashSet<Category>();

}
