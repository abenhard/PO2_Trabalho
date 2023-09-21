create table ufs(
    id serial not null primary key,
    nome varchar(2) not null
);
create table cidades(
    id serial not null primary key,
    iduf integer not null,
    nome varchar(100) not null,
    foreign key (iduf) references ufs(id)
);
create table usuarios(
    id serial not null primary key,
    login varchar(100) not null,
    senha varchar(100) not null ,
    permissao varchar(20),
    nome varchar(70) not null,
    telefone archar(70),
    cpf varchar(11) not null unique,
    data_nascimento DATE
  );
CREATE TABLE enderecos (
    id serial NOT NULL PRIMARY KEY,
    complemento varchar(150),
    bairro varchar(100) NOT NULL,
    rua varchar(100)NOT NULL,
    cep varchar(9) NOT NULL,
    numero varchar(15) NOT NULL,
    idcidade integer NOT NULL,
    idusuario integer,
    FOREIGN KEY (idcidade) REFERENCES cidades(id),
    FOREIGN KEY (idusuario) REFERENCES usuarios(id)
);
CREATE TABLE marcas(
    id serial not null primary key,
    nome varchar(70) not null
);
CREATE TABLE produtos(
    id serial not null primary key,
    nome varchar(70) not null,
    descricao varchar(170) not null,
    precobase numeric(12,2) not null,
    idmarca integer not null,
    FOREIGN KEY (idmarca) REFERENCES marcas(id)
);
CREATE table categorias(
    id serial not null primary key,
    nome varchar(70) not null
);
