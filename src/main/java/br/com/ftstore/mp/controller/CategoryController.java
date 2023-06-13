package br.com.ftstore.mp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.ftstore.mp.exception.ResponseGenericException;
import br.com.ftstore.mp.model.Category;
import br.com.ftstore.mp.service.CategoryService;

@RestController
@RequestMapping(value = "/api/v1/category")
@CrossOrigin("*")
public class CategoryController {
	
	@Autowired
	private CategoryService service;
	
	@GetMapping
	public ResponseEntity<Object> findAll() {
		List<Category> result = service.findAll();
		
		return ResponseEntity.ok().body(ResponseGenericException.response(result));
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> findById(@PathVariable Long id) {
		Category result = service.findById(id);
		
		return ResponseEntity.ok().body(ResponseGenericException.response(result));
	}
	
	@PostMapping(value = "/save")
	public ResponseEntity<Object> save(@RequestBody Category category) {
		Category result = service.save(category);
		
		return ResponseEntity.ok().body(ResponseGenericException.response(result));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
}
