package br.ufsm.poow2.biblioteca_rest.service;

import br.ufsm.poow2.biblioteca_rest.DTO.LoanDto;
import br.ufsm.poow2.biblioteca_rest.common.ApiResponse;
import br.ufsm.poow2.biblioteca_rest.exception.LoanException;
import br.ufsm.poow2.biblioteca_rest.model.Book;
import br.ufsm.poow2.biblioteca_rest.model.Loan;
import br.ufsm.poow2.biblioteca_rest.model.User;
import br.ufsm.poow2.biblioteca_rest.repository.BookRepository;
import br.ufsm.poow2.biblioteca_rest.repository.LoanRepository;
import br.ufsm.poow2.biblioteca_rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class LoanService {

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    LoanException loanException;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<ApiResponse> addLoan(LoanDto loanDto, Book book, User user) {

        ResponseEntity<ApiResponse> response;
        Loan loan = new Loan();
        loan.update(loanDto, user, book);
        Map<String, String> handleErrors = loanException.handleAddLoanErrors(loan, book, user);

        if (handleErrors.isEmpty()) {
            try {
                book.setInUseQuantity(book.getInUseQuantity() + 1);
                bookRepository.save(book);
                loanRepository.save(loan);
                response = ResponseEntity.status(HttpStatus.CREATED).body(
                        new ApiResponse(true, "Novo empréstimo registrado com sucesso!"));
            } catch (DataAccessException e) {
                response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                        new ApiResponse(false, "Ocorreu um erro ao acessar o banco de dados. Por favor, tente novamente mais tarde."));
            }
        }
        else
        {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse(false, "Falha ao criar novo empréstimo.", handleErrors)
            );
        }
        return response;
    }

    public LoanDto getLoan(Loan loan) {
        LoanDto dto = new LoanDto();
        dto.update(loan);
        return dto;
    }

    public List<Loan> findAllLoans() {
        List<Loan> loanList = loanRepository.findAll();
        return loanList;
    }

    public LoanDto getLoan(Loan loan, User user, Book book) {
        LoanDto loanDto = new LoanDto();
        loanDto.setLoanDate(loan.getLoanDate());
        loanDto.setReturnDate(loan.getReturnDate());
        loanDto.setUserId(user.getId());
        loanDto.setBookId(book.getId());
        loanDto.setStatus(loan.getStatus());
        loanDto.setExtended(loan.isExtended());
        return loanDto;
    }

    public void deleteLoanById(Integer loanId) {
        loanRepository.deleteById(loanId);
    }


    public ResponseEntity<ApiResponse> extendLoan(Integer idEmprestimo) {
        ResponseEntity<ApiResponse> response;
        Loan loan = loanRepository.findById(idEmprestimo).get();
        Map<String, String> handleErrors = loanException.handleExtendLoanErrors(loan);

        if (handleErrors.isEmpty()) {
            try {
                Calendar cal = Calendar.getInstance();
                cal.setTime(loan.getReturnDate());
                cal.add(Calendar.DATE, 7);
                loan.setReturnDate(new java.sql.Date(cal.getTime().getTime()));
                loan.setExtended(true);
                loanRepository.save(loan);
                response = ResponseEntity.status(HttpStatus.CREATED).body(
                        new ApiResponse(true, "Empréstimo extendido com sucesso!"));
            } catch (DataAccessException e) {
                response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                        new ApiResponse(false, "Ocorreu um erro ao acessar o banco de dados. Por favor, tente novamente mais tarde."));
            }
        }
        else
        {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse(false, "Falha ao extender empréstimo.", handleErrors)
            );
        }
        return response;
    }

    public ResponseEntity<ApiResponse> returnBook(LoanDto loanDto) {
        ResponseEntity<ApiResponse> response;
        Map<String, String> handleErrors = loanException.handleReturnBookErrors(loanDto.getId());

        if (handleErrors.isEmpty()) {
            try {
                String messageSuccess = "Empréstimo encerrado com sucesso!";
                if (loanDto.getStatus() == Loan.LoanStatus.DELAYED) {
                    long diff = loanDto.getReturnDate().getTime() - Calendar.getInstance().getTime().getTime();
                    int daysLate = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                    float fine = daysLate * 1;
                    messageSuccess += " Atraso no empréstimo de " + daysLate + ". Cobre uma multa de R$" + fine + " do usuário.";
                }
                Book book = bookRepository.findById(loanDto.getBookId()).get();
                book.setInUseQuantity(book.getInUseQuantity() - 1);

                loanRepository.deleteById(loanDto.getId());
                bookRepository.save(book);
                response = ResponseEntity.status(HttpStatus.CREATED).body(
                        new ApiResponse(true, messageSuccess));
            } catch (DataAccessException e) {
                response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                        new ApiResponse(false, "Ocorreu um erro ao acessar o banco de dados. Por favor, tente novamente mais tarde."));
            }
        }
        else
        {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse(false, "Falha ao encerrar empréstimo.", handleErrors)
            );
        }
        return response;
    }

}
