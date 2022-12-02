CREATE TABLE Usuario
(
    idUsuario        SERIAL PRIMARY KEY,
    nomeUsuario      VARCHAR(250),
    nicknameUsuario  VARCHAR(250),
    emailUsuario     VARCHAR(250) NOT NULL,
    senhaUsuario     VARCHAR(250) NOT NULL,
    permissaoUsuario CHAR(3)      NOT NULL,
    tokenUsuario     VARCHAR(1250) NOT NULL,
    CHECK (permissaoUsuario = 'adm' OR permissaoUsuario = 'usr'
) ,
    UNIQUE (idUsuario, nicknameUsuario, emailUsuario)
);

CREATE TABLE Genero
(
    idGenero   SERIAL PRIMARY KEY,
    nomeGenero VARCHAR(250) NOT NULL,
    UNIQUE (nomeGenero)
);

CREATE TABLE Autor
(
    idAutor        SERIAL PRIMARY KEY,
    nomeAutor      VARCHAR(250) NOT NULL,
    dataNascAutor  DATE         NOT NULL,
    dataMorteAutor DATE,
    descricaoAutor VARCHAR(250),
    urlFotoAutor   VARCHAR(250)
);


CREATE TABLE Livro
(
    idLivro        SERIAL PRIMARY KEY,
    nomeLivro      VARCHAR(250) NOT NULL,
    descricaoLivro VARCHAR(250),
    idAutor        INT          NOT NULL,
    urlCapaLivro   VARCHAR(250),
    urlLivro       VARCHAR(250),
    qntdTotalLivro INT          NOT NULL,
    qntdEmUsoLivro INT          NOT NULL
);

CREATE TABLE UsuarioLivro
(
    idUsuarioLivro             SERIAL PRIMARY KEY,
    dataEmprestimoUsuarioLivro DATE         NOT NULL,
    dataDevolucaoUsuarioLivro  DATE         NOT NULL,
    idUsuario                  INT          NOT NULL,
    idLivro                    INT          NOT NULL,
    statusUsuarioLivro         CHAR(1)      NOT NULL,
    CHECK (
        statusUsuarioLivro = 'A' OR
        statusUsuarioLivro = 'B' OR
        statusUsuarioLivro = 'L'
    )
);

CREATE TABLE LivroGenero
(
    idLivro                    INT          NOT NULL,
    idGenero                   INT          NOT NULL
);

CREATE TABLE AutorGenero
(
    idAutor                    INT          NOT NULL,
    idGenero                   INT          NOT NULL
);

ALTER TABLE Livro
    ADD FOREIGN KEY (idAutor) REFERENCES Autor (idAutor);
ALTER TABLE UsuarioLivro
    ADD FOREIGN KEY (idUsuario) REFERENCES Usuario (idUsuario);
ALTER TABLE UsuarioLivro
    ADD FOREIGN KEY (idLivro) REFERENCES Livro (idLivro);
ALTER TABLE LivroGenero
    ADD FOREIGN KEY (idLivro) REFERENCES Livro (idLivro);
ALTER TABLE LivroGenero
    ADD FOREIGN KEY (idGenero) REFERENCES Genero (idGenero);
ALTER TABLE AutorGenero
    ADD FOREIGN KEY (idAutor) REFERENCES Autor (idAutor);
ALTER TABLE AutorGenero
    ADD FOREIGN KEY (idGenero) REFERENCES Genero (idGenero);


INSERT INTO Usuario (nomeUsuario, emailUsuario, senhaUsuario, permissaoUsuario)
VALUES ('Admin', 'admin.alexandria@admin.com', '1234', 'adm');