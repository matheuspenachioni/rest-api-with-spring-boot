package br.com.ftstore.mp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ftstore.mp.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
