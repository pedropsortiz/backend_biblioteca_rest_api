package br.ufsm.poow2.biblioteca_rest.exception;

import br.ufsm.poow2.biblioteca_rest.common.ApiResponse;
import br.ufsm.poow2.biblioteca_rest.model.Book;
import br.ufsm.poow2.biblioteca_rest.model.Loan;
import br.ufsm.poow2.biblioteca_rest.model.User;
import br.ufsm.poow2.biblioteca_rest.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class LoanException {

    @Autowired
    LoanRepository loanRepository;

    public Map<String, String> handleAddLoanErrors(Loan loan, Book book, User user) {
        ApiResponse apiResponse = new ApiResponse();

        if (isValidLoanDates(loan)){
            apiResponse.addError("loan_date", "O formato das datas de empréstimo e de retorno não é válido.");
        }
        if (hasOverdueLoan(user)) {
            apiResponse.addError("user", "Usuário não pode realizar novos empréstimos com empréstimos atrasados.");
        }

        if (!isBookAvailable(book)) {
            apiResponse.addError("book", "O livro não está disponível para empréstimo.");
        }

        return apiResponse.getErrors();
    }

    public Map<String, String> handleExtendLoanErrors(Loan loan){
        ApiResponse apiResponse = new ApiResponse();

        if(isLoanValid(loan.getId())){
            apiResponse.addError("loan", "O empréstimo selecionado não existe ou não foi encontrado.");
        }
        if(!loan.isExtended()){
            apiResponse.addError("status", "O empréstimo selecionado já foi extendido uma vez.");
        }
        if(loan.getStatus() != Loan.LoanStatus.APPROVED){
            apiResponse.addError("extended", "O status do empréstimo não é válido para extensão de data.");
        }

        return apiResponse.getErrors();
    }

    public Map<String, String> handleReturnBookErrors(Integer idLoan){
        ApiResponse apiResponse = new ApiResponse();

        if(isLoanValid(idLoan)){
            apiResponse.addError("loan", "O empréstimo selecionado não existe ou não foi encontrado.");
        }

        return apiResponse.getErrors();
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
