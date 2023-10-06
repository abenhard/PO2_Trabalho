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
    login varchar(100) not null unique,
    senha varchar(100) not null ,
    permissao varchar(20),
    nome varchar(70) not null,
    telefone varchar(70),
    cpf varchar(11) not null unique,
    datanascimento DATE
  );
CREATE TABLE enderecos (
    id serial NOT NULL PRIMARY KEY,
    complemento varchar(150),
    bairro varchar(100) NOT NULL,
    rua varchar(100)NOT NULL,
    cep varchar(9) NOT NULL,
    numero varchar(15) NOT NULL,
    idcidade integer NOT NULL,
    idusuario integer NOT NULL,
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
CREATE TABLE produto_categoria(
     idproduto INT NOT NULL,
     idcategoria INT NOT NULL,
     FOREIGN KEY (idcategoria) REFERENCES categorias(id),
     FOREIGN KEY (idproduto) REFERENCES produtos(id)
);
CREATE table carrinhos(
    id serial not null primary key,
    idusuario INT NOT NULL,
    precototal numeric(12,2) not null,
    FOREIGN KEY (idusuario) REFERENCES usuarios(id)
);
CREATE TABLE produto_carrinho(
    id serial not null primary key,
    idcarrinho INT NOT NULL,
    idproduto INT NOT NULL,
    quantidade INT NOT NULL,
    FOREIGN KEY (idcarrinho) REFERENCES carrinhos(id),
    FOREIGN KEY (idproduto) REFERENCES produtos(id)
);
CREATE TABLE vendas(
    id serial not null primary key,
    idusuario INT NOT NULL,
    idendereco INT NOT NULL,
    precototal numeric(12,2) not null,
    datacriacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(15) NOT NULL,
    FOREIGN KEY (idusuario) REFERENCES usuarios(id),
    FOREIGN KEY (idendereco) REFERENCES enderecos(id)
);
CREATE TABLE produto_venda(
    id serial NOT NULL PRIMARY KEY ,
    idvenda INT NOT NULL,
    idproduto INT NOT NULL,
    quantidade INT NOT NULL ,
    FOREIGN KEY (idvenda) REFERENCES vendas(id),
    FOREIGN KEY (idproduto) REFERENCES produtos(id)
);
