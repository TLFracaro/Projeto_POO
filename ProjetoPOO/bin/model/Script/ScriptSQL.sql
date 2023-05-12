CREATE DATABASE holerite;
USE holerite;

DROP DATABASE holerite;

CREATE TABLE Pessoa(
	cpf varchar(11) primary key,
    rg varchar (9),
    nome text not null,
    cep varchar(8) not null,
    nascimento date
);
CREATE TABLE Endereco(
	cep varchar(9) primary key,
    logradouro text not null,
    bairro text not null,
    cidade text not null,
    uf varchar(2),
    numero int,
    pais text
);
CREATE TABLE Funcionario(
	cpf varchar(11),
    rg varchar(9),
    email text not null,
    senha text not null,
    privilegio boolean not null,
    cargo text not null,
    primary key (cpf, rg),
    foreign key (cpf, rg) references Pessoa(cpf, rg)
);
CREATE TABLE Telefone(
	cpf varchar(11),
    rg varchar(9),
    telefone varchar(11) primary key,
    foreign key (cpf, rg) references Pessoa(cpf, rg)
);
CREATE TABLE Cep(
	cpf varchar(11),
    rg varchar(9),
    cep varchar(9) primary key,
    foreign key (cpf, rg) references Pessoa(cpf, rg),
    foreign key (cep) references Endereco(cep)
);

SELECT P.*, F.email, F.senha, F.privilegio, F.cargo, E.logradouro, E.bairro, E.cidade, E.uf, E.numero, E.pais
FROM (Pessoa P, Cep C)
INNER JOIN (Funcionario F, Endereco E)
ON P.cpf = F.cpf
AND P.cep = C.cep;

SELECT Telefone.cpf, telefone FROM Pessoa, Telefone
WHERE Pessoa.cpf = Telefone.cpf;