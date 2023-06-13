package br.com.ftstore.mp.controller;

import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.ftstore.mp.exception.ResponseGenericException;
import br.com.ftstore.mp.model.Customer;
import br.com.ftstore.mp.service.CustomerService;
import br.com.ftstore.mp.service.ReportService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/v1/customer")
@CrossOrigin("*")
public class CustomerController {
	
	@Autowired
	private CustomerService cService;
	
	@Autowired
	private ReportService rService;
	
	@GetMapping
	public ResponseEntity<Object> findAll() {
		List<Customer> result = cService.findAll();
		
		return ResponseEntity.ok().body(ResponseGenericException.response(result));
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> findById(@PathVariable Long id) {
		Customer result = cService.findById(id);
		
		return ResponseEntity.ok().body(ResponseGenericException.response(result));
	}
	
	@PostMapping(value = "/save")
	public ResponseEntity<Object> save(@RequestBody Customer customer) {
		Customer result = cService.save(customer);
		
		return ResponseEntity.ok().body(ResponseGenericException.response(result));
	}
	
	@DeleteMapping(value = "/delete")
	public ResponseEntity<Object> delete(@PathVariable Long id){
		cService.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "report", produces = "application/text")
	public ResponseEntity<String> report(HttpServletRequest request) throws Exception {
		byte[] pdf = rService.generateReport("customer-report", request.getServletContext());
		String base64PDF = "data:application/pdf;base64,"+ Base64.encodeBase64String(pdf);
		
		return new ResponseEntity<String>(base64PDF, HttpStatus.OK);
	}
}
