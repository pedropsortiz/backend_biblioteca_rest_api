package br.ufsm.poow2.biblioteca_rest.model;

import br.ufsm.poow2.biblioteca_rest.DTO.LoanDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

/**

 Uma classe que representa um empréstimo.
 Regras:
 O campo "id" deve ser único para cada empréstimo e não pode ser alterado.
 O campo "loanDate" deve conter a data em que o empréstimo foi realizado e não pode ser nulo.
 O campo "returnDate" deve conter a data de devolução prevista do empréstimo e não pode ser nulo. Além disso, deve ser posterior à data de empréstimo.
 O campo "user" deve conter informações sobre o usuário que realizou o empréstimo e não pode ser nulo.
 O campo "book" deve conter informações sobre o livro que foi emprestado e não pode ser nulo.
 O campo "status" deve conter o status atual do empréstimo e não pode ser nulo. O status pode ser "aprovado", "esperando" ou "atrasado".
 Um empréstimo só pode ser realizado se o livro estiver disponível e o usuário não tiver nenhum empréstimo atrasado.
 O prazo de devolução de um empréstimo é de 7 dias, a menos que seja prorrogado.
 Um usuário só pode solicitar a prorrogação de um empréstimo uma vez.
 Se o livro não for devolvido no prazo de devolução ou prorrogado, o status do empréstimo será alterado para "atrasado" e o usuário será cobrado uma taxa de atraso.
 O usuário só poderá realizar novos empréstimos depois de devolver todos os livros emprestados e pagar quaisquer taxas de atraso.
 "Aprovado" significa que o empréstimo foi concedido e o livro foi retirado pelo usuário. "Esperando" significa que o usuário solicitou o empréstimo, mas o livro ainda não está disponível. "Atrasado" significa que o prazo de devolução do empréstimo expirou.
 */

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

    @ApiModelProperty(example = "0")
    @Column(name = "extended", nullable = false)
    private boolean extended;

    public enum LoanStatus {
        DELAYED, APPROVED, WAITING
    }

    public void update(LoanDto newValues, User user, Book book) {

        java.sql.Date loanDate = new java.sql.Date(new java.util.Date().getTime());
        this.loanDate = loanDate;
        Calendar cal = Calendar.getInstance();
        cal.setTime(getLoanDate());
        cal.add(Calendar.DATE, 7);
        java.sql.Date returnDate = new java.sql.Date(cal.getTime().getTime());
        this.returnDate = returnDate;
        this.extended = newValues.isExtended();
        this.user = user;
        this.book = book;
        this.status = newValues.getStatus();
    }

}
