CREATE TABLE Usuario
(
    idUsuario        INT          NOT NULL,
    nomeUsuario      VARCHAR(250),
    nicknameUsuario  VARCHAR(250),
    emailUsuario     VARCHAR(250) NOT NULL,
    senhaUsuario     VARCHAR(250) NOT NULL,
    permissaoUsuario CHAR(3)      NOT NULL,
    tokenUsuario     VARCHAR(1250) NOT NULL,
    PRIMARY KEY (idUsuario),
    CHECK (permissaoUsuario = 'adm' OR permissaoUsuario = 'usr'
) ,
    UNIQUE (idUsuario, nicknameUsuario, emailUsuario)
);

CREATE TABLE Genero
(
    idGenero   INT          NOT NULL,
    nomeGenero VARCHAR(250) NOT NULL,
    UNIQUE (nomeGenero),
    PRIMARY KEY (idGenero)
);

CREATE TABLE Autor
(
    idAutor        INT          NOT NULL,
    nomeAutor      VARCHAR(250) NOT NULL,
    dataNascAutor  DATE         NOT NULL,
    dataMorteAutor DATE,
    descricaoAutor VARCHAR(250),
    urlFotoAutor   VARCHAR(250),
    PRIMARY KEY (idAutor)
);


CREATE TABLE Livro
(
    idLivro        INT          NOT NULL,
    nomeLivro      VARCHAR(250) NOT NULL,
    descricaoLivro VARCHAR(250),
    idAutor        INT          NOT NULL,
    urlCapaLivro   VARCHAR(250),
    urlLivro       VARCHAR(250),
    qntdTotalLivro INT          NOT NULL,
    qntdEmUsoLivro INT          NOT NULL,
    PRIMARY KEY (idLivro)
);

CREATE TABLE UsuarioLivro
(
    idUsuarioLivro             INT          NOT NULL,
    dataEmprestimoUsuarioLivro DATE         NOT NULL,
    dataDevolucaoUsuarioLivro  DATE         NOT NULL,
    idUsuario                  INT          NOT NULL,
    idLivro                    INT          NOT NULL,
    statusUsuarioLivro         CHAR(1)      NOT NULL,
    CHECK (
        statusUsuarioLivro = 'A' OR
        statusUsuarioLivro = 'B' OR
        statusUsuarioLivro = 'L'
    ),
    PRIMARY KEY (idUsuarioLivro)
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


-- INSERT INTO Usuario (nomeUsuario, emailUsuario, senhaUsuario, permissaoUsuario)
-- VALUES ('Admin', 'admin.alexandria@admin.com', '1234', 'adm');