package br.ufsm.poow2.biblioteca_rest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birth_date", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date birthDate;

    @Column(name = "death_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date deathDate;

    @Column(name = "description")
    private String description;

    @Column(name = "photo_url")
    private String photoUrl;

    public void update(Author newValues) {
        this.name = newValues.name;
        this.description = newValues.description;
        this.deathDate = newValues.deathDate;
        this.photoUrl = newValues.photoUrl;
        this.birthDate = newValues.birthDate;
    }
}
