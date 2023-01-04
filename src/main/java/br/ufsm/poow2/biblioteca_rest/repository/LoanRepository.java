package br.ufsm.poow2.biblioteca_rest.repository;

import br.ufsm.poow2.biblioteca_rest.model.Loan;
import br.ufsm.poow2.biblioteca_rest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {
    @Transactional(readOnly = true)
    List<Loan> findLoansByUser(User user);
}
