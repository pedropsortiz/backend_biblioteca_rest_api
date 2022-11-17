package br.ufsm.poow2.biblioteca_rest.Model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Livro {

    private int idLivro;
    private String nomeLivro;
    private String descricaoLivro;
    private int idAutor;
    private String capaLivro;
    private String qntdTotalLivro;
    private String qntdEmUsoLivro;

}
