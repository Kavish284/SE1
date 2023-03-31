package edu.iu.p565.customerservice.controller;

import java.util.List;

import javax.validation.Valid;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import edu.iu.p565.customerservice.model.Customer;
import edu.iu.p565.customerservice.repository.InMemoryCustomerRepository;

@RestController
@RequestMapping("/api/customers")
@Validated
public class CustomerController {

    private final InMemoryCustomerRepository repository;

    @Autowired
    public CustomerController(InMemoryCustomerRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") int id) {
        Customer customer = repository.getCustomerById(id);
        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
        int id = repository.create(customer);
        customer.setId(id);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@Valid @RequestBody Customer customer, @PathVariable("id") int id) {
        repository.update(customer, id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") int id) {
        Customer customer = repository.getCustomerById(id);
        if (customer != null) {
            repository.delete(id);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }
    }
}
