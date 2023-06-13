package br.com.ftstore.mp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ftstore.mp.exception.types.ObjectNotFoundException;
import br.com.ftstore.mp.model.Product;
import br.com.ftstore.mp.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	public List<Product> findAll() {
		return repository.findAll();
	}
	
	public Product findById(Long id) {
		return repository.findById(id).orElseThrow(() ->  new ObjectNotFoundException("A categoria não foi encontrada! ID: "+ id));
	}
	
	public Product save(Product product) {
		return repository.saveAndFlush(product);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
}
