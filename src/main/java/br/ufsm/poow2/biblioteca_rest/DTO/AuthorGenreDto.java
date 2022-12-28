package br.ufsm.poow2.biblioteca_rest.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorGenreDto {

    private Integer id;
    private Integer authorId;
    private Integer genreId;
}
