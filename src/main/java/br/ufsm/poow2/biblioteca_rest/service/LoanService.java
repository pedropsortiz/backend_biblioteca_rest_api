package br.ufsm.poow2.biblioteca_rest.service;

import br.ufsm.poow2.biblioteca_rest.DTO.LoanDto;
import br.ufsm.poow2.biblioteca_rest.model.Book;
import br.ufsm.poow2.biblioteca_rest.model.Loan;
import br.ufsm.poow2.biblioteca_rest.model.User;
import br.ufsm.poow2.biblioteca_rest.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    @Autowired
    LoanRepository loanRepository;

    public void addLoan(LoanDto dto, Book book, User user) {
        Loan loan = new Loan();
        loan.setUser(user);
        loan.setBook(book);
        loan.setStatus(dto.getStatus());
        loan.setLoanDate(dto.getLoanDate());
        loan.setReturnDate(dto.getReturnDate());
        loanRepository.save(loan);
    }

    public LoanDto getLoan(Loan loan) {
        LoanDto dto = new LoanDto();
        dto.setId(loan.getId());
        dto.setBookId(loan.getBook().getId());
        dto.setUserId(loan.getUser().getId());
        dto.setStatus(loan.getStatus());
        dto.setLoanDate(loan.getLoanDate());
        dto.setReturnDate(loan.getReturnDate());
        return dto;
    }

    public List<LoanDto> findAllLoans() {
        List<Loan> loanList = loanRepository.findAll();

        List<LoanDto> dtoList = new ArrayList<>();
        for (Loan loan: loanList) {
            dtoList.add(getLoan(loan));
        }
        return dtoList;
    }

    public void updateLoan(LoanDto loanDto, Integer loanId) throws Exception {
        Optional<Loan> loanOptional = loanRepository.findById(loanId);
        if (!loanOptional.isPresent()) {
            throw new Exception("Loan does not exist!");
        }
        Loan loan = loanOptional.get();

        loan.setStatus(loanDto.getStatus());
        loan.setLoanDate(loanDto.getLoanDate());
        loan.setReturnDate(loanDto.getReturnDate());
        loanRepository.save(loan);
    }

    public void deleteLoanById(Integer loanId) {
        loanRepository.deleteById(loanId);
    }

}
