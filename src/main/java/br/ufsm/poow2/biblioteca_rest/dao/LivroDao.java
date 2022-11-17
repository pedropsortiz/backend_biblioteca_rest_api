package br.ufsm.poow2.biblioteca_rest.Dao;

import br.ufsm.poow2.biblioteca_rest.Model.Livro;

import java.util.ArrayList;

public class LivroDao {

    public ArrayList<Livro> getLivros(){
        ArrayList<Livro> livros = new ArrayList<>();

        livros.add(new Livro(
                1,
                "Sussurros na Escuridão",
                "Neste conto de horror cósmico, Lovecraft envolve o leitor em uma narrativa rica em detalhes, onde as Criaturas Siderais habitantes de Yuggoth – o nono planeta do sistema solar – invadem uma região em Vermont, fato que é atestado por um morador local que se comunica com um importante estudioso por meio de cartas.",
                1,
                "/resources/templates/images/capa.png",
                "15",
                "12")
        );

        livros.add(new Livro(
                2,
                "O chamado de Cthulu",
                "The Call of Cthulhu é um conto de horror do escritor norte-americano H. P. Lovecraft, contando a investigação sobre um ser extraterrestre dos \"antigos\", que na mitologia lovecraftiana seriam criaturas cósmicas, vindas à Terra antes desta abrigar a vida.",
                1,
                "/resources/templates/images/capa2.png",
                "28",
                "14")
        );

        livros.add(new Livro(
                3,
                "O Barril de Amontillado",
                "O Barril de \"Amontillado\", escrito por Edgar Allan Poe, é um conto narrado em primeira pessoa, no qual há um narrador-personagem, chamado Montresor, que demonstra ser um indivíduo formal e educado. No conto, o narrador-personagem conta a história na qual ele participa como personagem.",
                2,
                "/resources/templates/images/capa3.png",
                "50",
                "3")
        );


        return livros;
    }

    public Livro getLivro(){
        return new Livro(
                4,
                "O Cortiço\n",
                "Foi publicado em 1890 e faz parte do movimento naturalista do Brasil. A obra retrata a vida das pessoas simples em um cortiço (habitação coletiva) do Rio de Janeiro. Com um teor crítico, trata-se de uma exímia representação da realidade brasileira do século XIX.",
                3,
                "/resources/templates/images/capa4.png",
                "96",
                "57");
    }

}