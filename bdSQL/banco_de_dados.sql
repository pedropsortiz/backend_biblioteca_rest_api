CREATE TABLE Usuario
(
 idUsuario SERIAL PRIMARY KEY,
 nomeUsuario VARCHAR(250),
 emailUsuario VARCHAR(250) NOT NULL,
 senhaUsuario VARCHAR(250) NOT NULL,
 permissaoUsuario CHAR(3) NOT NULL,
 CHECK (permissaoUsuario = 'adm' OR permissaoUsuario = 'usr'),
 UNIQUE (idUsuario, nomeUsuario, emailUsuario)
);

CREATE TABLE Genero
(
 idGenero SERIAL PRIMARY KEY,
 nomeGenero VARCHAR(250) NOT NULL,
 UNIQUE (nomeGenero)
);

CREATE TABLE Autor
(
 idAutor SERIAL PRIMARY KEY,
 nomeAutor VARCHAR(250) NOT NULL,
 dataNascAutor DATE NOT NULL,
 dataMorteAutor DATE,
 descricaoAutor VARCHAR(250),
 fotoAutor VARCHAR(250)
);


CREATE TABLE Livro
(
    idLivro SERIAL PRIMARY KEY,
    nomeLivro VARCHAR(250) NOT NULL,
    descricaoLivro VARCHAR(250) NOT NULL,
    idGenero INT NOT NULL,
    idAutor INT NOT NULL,
    capaLivro VARCHAR(250),
    qntdTotalLivro INT,
    qntdEmUsoLivro INT
);

CREATE TABLE UsuarioLivro
(
 idUsuarioLivro SERIAL PRIMARY KEY,
 dataEmprestimoUsuarioLivro DATE,
 dataDevolucaoUsuarioLivro DATE NOT NULL,
 idUsuario INT NOT NULL,
 idLivro INT NOT NULL,
 statusUsuarioLivro VARCHAR(250) NOT NULL
);

ALTER TABLE Livro ADD FOREIGN KEY(idGenero) REFERENCES Genero (idGenero);
ALTER TABLE Livro ADD FOREIGN KEY(idAutor) REFERENCES Autor (idAutor);
ALTER TABLE UsuarioLivro ADD FOREIGN KEY(idUsuario) REFERENCES Usuario (idUsuario);
ALTER TABLE UsuarioLivro ADD FOREIGN KEY(idLivro) REFERENCES Livro (idLivro);

INSERT INTO Usuario (nomeUsuario, emailUsuario, senhaUsuario, permissaoUsuario) 
VALUES ('Admin', 'admin_alexandria@admin.com', '1234', 'adm');
