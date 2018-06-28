package banking.customers.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import banking.customers.domain.entity.Customer;

public interface CustomerRepositoryN extends JpaRepository<Customer, Long> {

}

