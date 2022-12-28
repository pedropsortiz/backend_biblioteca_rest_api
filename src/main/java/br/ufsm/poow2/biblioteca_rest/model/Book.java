package br.ufsm.poow2.biblioteca_rest.model;

import br.ufsm.poow2.biblioteca_rest.DTO.BookDto;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

    @Column(name = "name")
    @NotNull
    @NotBlank
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @NotNull
    @NotBlank
    private Author author;

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

    public void update(BookDto newValues) {
        this.name = newValues.getName();
        this.description = newValues.getDescription();
        this.coverUrl = newValues.getCoverUrl();
        this.totalQuantity = newValues.getTotalQuantity();
        this.inUseQuantity = newValues.getInUseQuantity();
    }
}
