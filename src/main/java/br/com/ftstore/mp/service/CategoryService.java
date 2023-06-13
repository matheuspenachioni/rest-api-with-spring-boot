package br.com.ftstore.mp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ftstore.mp.exception.types.ObjectNotFoundException;
import br.com.ftstore.mp.exception.types.DataIntegrityViolationException;
import br.com.ftstore.mp.model.Category;
import br.com.ftstore.mp.model.Product;
import br.com.ftstore.mp.repository.CategoryRepository;
import br.com.ftstore.mp.repository.ProductRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository cRepository;
	
	@Autowired
	private ProductRepository pRepository;
	
	public List<Category> findAll() {
		return cRepository.findAll();
	}
	
	public Category findById(Long id) {
		return cRepository.findById(id).orElseThrow(() ->  new ObjectNotFoundException("A categoria não foi encontrada! ID: "+ id));
	}
	
	public Category save(Category category) {
		alreadyExists(category);
		
		return cRepository.saveAndFlush(category);
	}
	
	public void delete(Long id) {
		Category obj = findById(id);
		
		List<Product> products = pRepository.findByCategoriesContaining(obj);
        if (!products.isEmpty()) {
            throw new DataIntegrityViolationException("A categoria "+ obj.getName() +" está associada a um ou mais produtos e não pode ser deletada!");
        }
		
        cRepository.deleteById(id);
	}
	
	public void alreadyExists(Category category) {
		Optional<Category> obj = cRepository.findByName(category.getName());
		
		if(obj.isPresent() && obj.get().getId() != category.getId()) {
			throw new DataIntegrityViolationException("A categoria "+ category.getName() +" já existe!");
		}
	}
	
}
