package br.ufsm.poow2.biblioteca_rest.model;

import br.ufsm.poow2.biblioteca_rest.DTO.BookDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Uma classe que representa um livro.
 *
 * Regras:
 * - O campo "id" deve ser único para cada livro e não pode ser alterado.
 * - O campo "name" deve ser preenchido com o título do livro e não pode ser vazio ou nulo. Além disso, o tamanho mínimo é de 3 caracteres.
 * - O campo "description" deve conter uma breve descrição do livro e pode ser nulo. Se preenchido, o tamanho mínimo é de 10 caracteres.
 * - O campo "author" deve conter informações sobre o autor do livro e não pode ser nulo.
 * - O campo "coverUrl" deve conter a URL da capa do livro e pode ser nulo.
 * - O campo "totalQuantity" deve conter o número total de exemplares do livro disponíveis e deve ser maior ou igual a zero.
 * - O campo "inUseQuantity" deve conter o número de exemplares do livro que estão atualmente em uso e deve ser maior ou igual a zero. Ele também deve ser menor ou igual a "totalQuantity".
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    @NotNull
    @NotBlank
    private String title;

    @ApiModelProperty(example = "null")
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @NotNull
    @NotBlank
    private Author author;

    @ApiModelProperty(example = "null")
    @Column(name = "cover_url")
    private String coverUrl;

    @Column(name = "total_quantity")
    @NotNull
    @NotBlank
    private Integer totalQuantity;

    @Column(name = "in_use_quantity")
    @NotNull
    @NotBlank
    private Integer inUseQuantity;

    @Column(name = "edition")
    @NotNull
    @NotBlank
    private int edition;

    @Column(name = "publication_date")
    @NotNull
    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date publicationDate;

    public void update(BookDto newValues, Author author) {
        this.title = newValues.getTitle();
        this.description = newValues.getDescription();
        this.coverUrl = newValues.getCoverUrl();
        this.totalQuantity = newValues.getTotalQuantity();
        this.inUseQuantity = newValues.getInUseQuantity();
        this.author = author;
        this.edition = newValues.getEdition();
        this.publicationDate = newValues.getPublicationDate();
    }
}
