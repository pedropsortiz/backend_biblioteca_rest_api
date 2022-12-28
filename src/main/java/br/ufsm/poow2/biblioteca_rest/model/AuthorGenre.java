package br.ufsm.poow2.biblioteca_rest.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "author_gender")
public class AuthorGenre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Author author;

    @ManyToOne
    private Genre genre;

    public void update(AuthorGenre newValues) {
        this.author = newValues.author;
        this.genre = newValues.genre;
    }
}

