package br.com.ftstore.mp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.ftstore.mp.exception.types.ObjectNotFoundException;
import br.com.ftstore.mp.model.Customer;
import br.com.ftstore.mp.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
    private CustomerRepository repository;

    public List<Customer> findAll() {
        return repository.findAll();
    }
    
    public Customer findById(Long id) {
		return repository.findById(id).orElseThrow(() ->  new ObjectNotFoundException("O cliente não foi encontrado! ID: "+ id));
	}
    
    public Customer save(Customer customer) {
    	encryptPassword(customer);
        return repository.saveAndFlush(customer);
    }
    
    public void delete(Long id) {
		repository.deleteById(id);
	}
    
	public void encryptPassword(Customer customer){
        BCryptPasswordEncoder encrypt = new BCryptPasswordEncoder();
        String encryptedPassword = null;
        if (customer.getId() == null) {
            encryptedPassword = encrypt.encode(customer.getPassword());
            customer.setPassword(encryptedPassword);
        } else {
            if (!repository.findById(customer.getId()).get().getPassword()
                    .equals(customer.getPassword())) {
                encryptedPassword = encrypt.encode(customer.getPassword());
                customer.setPassword(encryptedPassword);
            }
        }
	}
}
