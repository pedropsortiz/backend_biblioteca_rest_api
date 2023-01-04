package br.ufsm.poow2.biblioteca_rest.exception;

import br.ufsm.poow2.biblioteca_rest.model.Book;
import br.ufsm.poow2.biblioteca_rest.model.Loan;
import br.ufsm.poow2.biblioteca_rest.model.User;
import br.ufsm.poow2.biblioteca_rest.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class LoanException {

    @Autowired
    LoanRepository loanRepository;

    @ExceptionHandler(Exception.class)
    public List<String> handleAddLoanErrors(Loan loan, Book book, User user) {
        List<String> errors = new ArrayList<>();

        if (isValidLoanDates(loan)){
            errors.add("O formato das datas de empréstimo e de retorno não é válido.");
        }
        if (hasOverdueLoan(user)) {
            errors.add("Usuário não pode realizar novos empréstimos com empréstimos atrasados.");
        }

        if (!isBookAvailable(book)) {
            errors.add("O livro não está disponível para empréstimo.");
        }

        return errors;
    }

    public List<String> handleExtendLoanErrors(Loan loan){
        List<String> errors = new ArrayList<>();

        if(isLoanValid(loan.getId())){
            errors.add("O empréstimo selecionado não existe ou não foi encontrado.");
        }
        if(!loan.isExtended()){
            errors.add("O empréstimo selecionado já foi extendido uma vez.");
        }
        if(loan.getStatus() != Loan.LoanStatus.APPROVED){
            errors.add("O status do empréstimo não é válido para extensão de data.");
        }

        return errors;
    }

    public List<String> handleReturnBookErrors(Integer idLoan){
        List<String> errors = new ArrayList<>();

        if(isLoanValid(idLoan)){
            errors.add("O empréstimo selecionado não existe ou não foi encontrado.");
        }

        return errors;
    }

    public boolean hasOverdueLoan(User user) {
        for (Loan loan : getLoans(user)) {
            if (loan.getStatus() == Loan.LoanStatus.DELAYED) {
                return true;
            }
        }
        return false;
    }

    public boolean isValidLoanDates(Loan loan) {
        return loan.getLoanDate().after(loan.getReturnDate());
    }


    public boolean isBookAvailable(Book book) {
        return book.getTotalQuantity() > book.getInUseQuantity();
    }

    private boolean isLoanValid(Integer idLoan) {
        return loanRepository.findById(idLoan).isPresent();
    }

    public List<Loan> getLoans(User user) {
        return loanRepository.findLoansByUser(user);
    }

}
