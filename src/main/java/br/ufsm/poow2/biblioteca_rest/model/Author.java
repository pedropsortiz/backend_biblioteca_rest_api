package br.ufsm.poow2.biblioteca_rest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;

/**
 * Regra 1: O ID do autor é gerado automaticamente pelo banco de dados e não pode ser alterado pelo usuário.
 * Regra 2: O nome do autor deve conter apenas letras, acentos e o caractere especial ç.
 * Regra 3: A data de nascimento do autor deve ser anterior à data atual.
 * Regra 4: A data de morte do autor pode ser nula, mas se for especificada, deve ser posterior à data de nascimento.
 * Regra 5: A descrição do autor pode conter qualquer tipo de caractere, incluindo caracteres especiais e números.
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
