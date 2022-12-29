package br.ufsm.poow2.biblioteca_rest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;

/**

 * Uma classe que representa um livro.
 *
 * Regras:
 * O campo 'id' deve ser único para cada livro e não pode ser alterado.
 * O campo 'name' deve ser preenchido com o título do livro e não pode ser vazio ou nulo. Além disso, o tamanho mínimo é de 3 caracteres.
 * O campo 'description' deve conter uma breve descrição do livro e pode ser nulo. Se preenchido, o tamanho mínimo é de 10 caracteres.
 * O campo 'author' deve conter informações sobre o autor do livro e não pode ser nulo.
 * O campo 'coverUrl' deve conter a URL da capa do livro e pode ser nulo.
 * O campo 'totalQuantity' deve conter o número total de exemplares do livro disponíveis e deve ser maior ou igual a zero.
 * O campo 'inUseQuantity' deve conter o número de exemplares do livro que estão atualmente em uso e deve ser maior ou igual a zero. Ele também deve ser menor ou igual a 'totalQuantity'.
 */

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

    @ApiModelProperty(example = "null")
    @Column(name = "description")
    private String description;

    @ApiModelProperty(example = "null")
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
