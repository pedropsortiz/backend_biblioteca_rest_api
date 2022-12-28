package br.ufsm.poow2.biblioteca_rest.model;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book_genre")
public class BookGenre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Book book;

    @ManyToOne
    private Genre genre;

    public void update(BookGenre newValues) {
        this.book = newValues.book;
        this.genre = newValues.genre;
    }

}
