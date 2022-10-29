CREATE TABLE Usuario
(
 idUsuario INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
 nomeUsuario VARCHAR(250),
 emailUsuario VARCHAR(250) NOT NULL,
 senhaUsuario VARCHAR(250) NOT NULL,
 permissaoUsuario CHAR(3) NOT NULL,
 CHECK (permissaoUsuario = 'adm' OR permissaoUsuario = 'usr'),
 UNIQUE (idUsuario, nomeUsuario, emailUsuario)
);

CREATE TABLE Genero
(
 idGenero INT PRIMARY KEY AUTO_INCREMENT,
 nomeGenero VARCHAR(250) NOT NULL,
 UNIQUE (nomeGenero)
);

CREATE TABLE Autor
(
 idAutor INT PRIMARY KEY AUTO_INCREMENT,
 nomeAutor VARCHAR(250) NOT NULL,
 dataNascAutor DATE NOT NULL,
 dataMorteAutor DATE,
 descricaoAutor VARCHAR(250),
 fotoAutor VARCHAR(250),
 CHECK (dataMorteAutor > undefined)
);


CREATE TABLE Livro
(
    idLivro INT PRIMARY KEY AUTO_INCREMENT,
    nomeLivro VARCHAR(250) NOT NULL,
    descricaoLivro VARCHAR(250) NOT NULL,
    idGenero INT NOT NULL,
    idAutor INT NOT NULL,
    capaLivro VARCHAR(250),
    qntdTotalLivro INT,
    qntdEmUsoLivro INT,
);

CREATE TABLE UsuarioLivro
(
 idUsuarioLivro INT PRIMARY KEY AUTO_INCREMENT,
 dataEmprestimoUsuarioLivro DATE,
 dataDevolucaoUsuarioLivro DATE NOT NULL,
 idUsuarioUsuarioLivro INT NOT NULL,
 idLivroUsuarioLivro INT NOT NULL,
 statusUsuarioLivro VARCHAR(250) NOT NULL,
);

ALTER TABLE Livro ADD FOREIGN KEY(idGenero) REFERENCES Genero (idGenero)
ALTER TABLE Livro ADD FOREIGN KEY(idAutor) REFERENCES Autor (idAutor)
ALTER TABLE UsuarioLivro ADD FOREIGN KEY(idUsuarioUsuarioLivro) REFERENCES Usuario (idUsuarioUsuarioLivro)
ALTER TABLE UsuarioLivro ADD FOREIGN KEY(idLivroUsuarioLivro) REFERENCES Livro (idLivroUsuarioLivro)
