create table ufs(
    id serial not null primary key,
    nome varchar(2) not null
);
create table cidades(
    id serial not null primary key,
    id_uf integer not null,
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
    id_cidade integer NOT NULL,
    id_usuario integer,
    FOREIGN KEY (id_cidade) REFERENCES cidades(id),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
);
CREATE TABLE marcas(
    id serial not null primary key,
    nome varchar(70) not null
);
CREATE TABLE produtos(
    id serial not null primary key,
    nome varchar(70) not null,
    descricao varchar(170) not null,
    preco_base numeric(12,2) not null,
    id_marca integer not null,
    FOREIGN KEY (id_marca) REFERENCES marcas(id)
);
CREATE table categorias(
    id serial not null primary key,
    nome varchar(70) not null
);
CREATE TABLE produto_categoria(
     id_produto INT NOT NULL,
     id_categoria INT NOT NULL,
     FOREIGN KEY (id_categoria) REFERENCES categorias(id),
     FOREIGN KEY (id_produto) REFERENCES produtos(id);
);
CREATE table carrinhos(
    id serial not null primary key,
    id_usuario INT NOT NULL,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
);
CREATE TABLE produto_carrinho(
    id serial not null primary key,
    id_carrinho INT NOT NULL,
    id_produto INT NOT NULL,
    quantidade INT NOT NULL,
    FOREIGN KEY (id_carrinho) REFERENCES carrinhos(id),
    FOREIGN KEY (id_produto) REFERENCES produtos(id)
);
CREATE TABLE vendas(
    id serial not null primary key,
    id_usuario INT NOT NULL,
    preco_total numeric(12,2) not null,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status ENUM('Completo', 'Cancelado', 'Cancelado') NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id);
);
CREATE TABLE produto_venda(
    id serial NOT NULL PRIMARY KEY ,
    id_venda INT NOT NULL,
    id_produto INT NOT NULL,
    quantidade INT NOT NULL ,
    FOREIGN KEY (id_venda) REFERENCES vendas(id),
    FOREIGN KEY (id_produto) REFERENCES produtos(id);
);
