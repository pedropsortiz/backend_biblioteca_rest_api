package br.ufsm.poow2.biblioteca_rest.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookGenreDto {

    private Integer id;
    private Integer bookId;
    private Integer genreId;
}
