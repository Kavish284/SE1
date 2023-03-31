package edu.iu.p565.customerservice.repository;

import edu.iu.p565.customerservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
