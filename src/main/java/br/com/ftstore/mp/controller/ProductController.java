package br.com.ftstore.mp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.ftstore.mp.exception.ResponseGenericException;
import br.com.ftstore.mp.model.Product;
import br.com.ftstore.mp.service.ProductService;

@RestController
@RequestMapping(value = "/api/v1/product")
@CrossOrigin("*")
public class ProductController {

	@Autowired
	private ProductService service;
	
	@GetMapping
	public ResponseEntity<Object> findAll() {
		List<Product> result = service.findAll();
		
		return ResponseEntity.ok().body(ResponseGenericException.response(result));
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> findById(@PathVariable Long id) {
		Product result = service.findById(id);
		
		return ResponseEntity.ok().body(ResponseGenericException.response(result));
	}
	
	@PostMapping(value = "/save")
	public ResponseEntity<Object> save(@RequestBody Product product) {
		Product result = service.save(product);
		
		return ResponseEntity.ok().body(ResponseGenericException.response(result));
	}
	
	@DeleteMapping(value = "/delete")
	public ResponseEntity<Object> delete(@PathVariable Long id){
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
}
