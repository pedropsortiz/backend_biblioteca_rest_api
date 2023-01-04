package br.ufsm.poow2.biblioteca_rest.DTO;

import br.ufsm.poow2.biblioteca_rest.model.Book;
import br.ufsm.poow2.biblioteca_rest.model.Loan;
import br.ufsm.poow2.biblioteca_rest.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.Calendar;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanDto {

    private Integer id;
    private Date loanDate;
    private Date returnDate;
    private Integer userId;
    private Integer bookId;
    private @NotNull @NotBlank Loan.LoanStatus status;
    private boolean extended;

    public void update(Loan newValues) {
        this.loanDate = newValues.getLoanDate();
        this.returnDate = newValues.getReturnDate();
        this.extended = newValues.isExtended();
        this.userId = newValues.getUser().getId();
        this.bookId = newValues.getBook().getId();
        this.status = newValues.getStatus();
    }

}
