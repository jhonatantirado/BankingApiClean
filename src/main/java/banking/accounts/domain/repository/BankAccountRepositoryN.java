package banking.accounts.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import banking.accounts.domain.entity.BankAccount;

public interface BankAccountRepositoryN extends JpaRepository<BankAccount, Long> {

}

