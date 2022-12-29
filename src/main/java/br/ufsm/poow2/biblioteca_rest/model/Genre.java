package br.ufsm.poow2.biblioteca_rest.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Uma classe que representa um gênero.
 *
 * Regras:
 * - O campo "id" deve ser único para cada gênero e não pode ser alterado. Ele deve ser um inteiro maior que zero.
 * - O campo "name" deve conter o nome do gênero e não pode ser vazio ou nulo. Além disso, o tamanho mínimo é de 3 caracteres e o tamanho máximo é de 100 caracteres. O nome não deve conter caracteres especiais, exceto o hífen (-) e o espaço ( ).
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "genre")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @NotNull
    @NotBlank
    private String name;

    public void update(Genre newValues) {
        this.name = newValues.name;
    }
}
