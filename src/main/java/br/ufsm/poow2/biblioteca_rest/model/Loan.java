package br.ufsm.poow2.biblioteca_rest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "loan")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "loan_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date loanDate;

    @Column(name = "return_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date returnDate;

    @ManyToOne
    private User user;

    @ManyToOne
    private Book book;

    @Column(name = "status", nullable = false)
    private LoanStatus status;

    public enum LoanStatus {
        DELAYED, APPROVED, WAITING
    }

    public void update(Loan newValues) {
        this.loanDate = newValues.loanDate;
        this.returnDate = newValues.returnDate;
        this.user = newValues.user;
        this.book = newValues.book;
        this.status = newValues.status;
    }
}
