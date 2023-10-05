INSERT INTO ufs (nome) VALUES ('AC'), ('AL'),('AP'),('AM'),('BA'), ('CE'),
                           ('DF'), ('ES'),('GO'),('MA'),('MT'),('MS'),
                           ('MG'),('PA'),('PB'),('PR'),('PE'),('PI'),
                           ('RJ'),('RN'), ('RS'), ('RO'),('RR'),('SC'),
                           ('SP'),('SE'),('TO');

-- Inserir cidades para o estado do Rio Grande do Sul (RS)
INSERT INTO cidades (iduf, nome) VALUES
                                     (21, 'Porto Alegre'),
                                     (21, 'Caxias do Sul'),
                                     (21, 'Pelotas'),
                                     (21, 'Canoas'),
                                     (21, 'Santa Maria'),
                                     (21, 'Gravataí'),
                                     (21, 'Novo Hamburgo'),
                                     (21, 'São Leopoldo'),
                                     (21, 'Rio Grande'),
                                     (21, 'Viamão');

-- Inserir cidades para o estado de Santa Catarina (SC)
INSERT INTO cidades (iduf, nome) VALUES
                                     (23, 'Florianópolis'),
                                     (23, 'Joinville'),
                                     (23, 'Blumenau'),
                                     (23, 'São José'),
                                     (23, 'Chapecó');

-- Inserir cidades para o estado de São Paulo (SP)
INSERT INTO cidades (iduf, nome) VALUES
                                     (24, 'São Paulo'),
                                     (24, 'Guarulhos'),
                                     (24, 'Campinas'),
                                     (24, 'São Bernardo do Campo'),
                                     (24, 'Santo André');

-- Inserir cidades para o estado de Minas Gerais (MG)
INSERT INTO cidades (iduf, nome) VALUES
                                     (13, 'Belo Horizonte'),
                                     (13, 'Uberlândia'),
                                     (13, 'Contagem'),
                                     (13, 'Juiz de Fora'),
                                     (13, 'Betim');

INSERT INTO usuarios (login, senha, permissao, nome, telefone, cpf, datanascimento) VALUES
          ('abf@gmail.com', '1234', 'ROLE_ADMIN', 'Alex Benhard Ferreira', '55991425592', '02884551345', '1990-03-21'),
          ('sarahjackson@gmail.com', 'sarahpass', 'ROLE_USER', 'Sarah Jackson', '5599333777', '11223344556', '1993-05-14'),
          ('danielbrown@gmail.com', 'danielpass', 'ROLE_USER', 'Daniel Brown', '5599888877', '99887766554', '1981-01-19'),
          ('oliviawilson@gmail.com', 'oliviapass', 'ROLE_USER', 'Olivia Wilson', '5599111122', '99886655447', '1988-07-23'),
          ('robertthomas@gmail.com', 'robertpass', 'ROLE_USER', 'Robert Thomas', '5599222211', '99885566742', '1972-04-15');
-- Inserir carrinhos para os usuários com ID específico
INSERT INTO carrinhos (idusuario, datacriacao, precototal) VALUES
                                                               (1, current_timestamp, 0.00), -- Carrinho para Alex Benhard Ferreira
                                                               (2, current_timestamp, 0.00), -- Carrinho para Sarah Jackson
                                                               (3, current_timestamp, 0.00), -- Carrinho para Daniel Brown
                                                               (4, current_timestamp, 0.00), -- Carrinho para Olivia Wilson
                                                               (5, current_timestamp, 0.00); -- Carrinho para Robert Thomas

-- Inserir endereços para o usuário com ID 1 (abf@gmail.com)
INSERT INTO enderecos (complemento, bairro, rua, cep, numero, idcidade, idusuario) VALUES ('Apto 101', 'Centro', 'Rua Principal', '12345-678', '123', 1, 1);

-- Inserir endereços para o usuário com ID 2 (sarahjackson@gmail.com)
INSERT INTO enderecos (complemento, bairro, rua, cep, numero, idcidade, idusuario) VALUES ('Casa 2', 'Bairro Novo', 'Rua da Paz', '54321-987', '456', 2, 2);

-- Inserir endereços para o usuário com ID 3 (danielbrown@gmail.com)
INSERT INTO enderecos (complemento, bairro, rua, cep, numero, idcidade, idusuario) VALUES ('Sala 303', 'Centro', 'Avenida Principal', '67890-321', '789', 3, 3);

-- Inserir endereços para o usuário com ID 4 (oliviawilson@gmail.com)
INSERT INTO enderecos (complemento, bairro, rua, cep, numero, idcidade, idusuario) VALUES ('Casa 1', 'Bairro Sul', 'Rua do Sol', '13579-246', '246', 4, 4);

-- Inserir endereços para o usuário com ID 5 (robertthomas@gmail.com)
INSERT INTO enderecos (complemento, bairro, rua, cep, numero, idcidade, idusuario) VALUES ('Apto 502', 'Centro', 'Rua Nova', '98765-432', '987', 5, 5);


INSERT INTO marcas (nome) VALUES
                              ('Nestle'), ('Apple'), ('Microsoft'), ('Samsung'), ('Toyota'),
                              ('Coca-Cola'), ('Amazon'), ('Google'), ('Sony'), ('Nike'),
                              ('Adidas'), ('BMW'), ('Mercedes-Benz'), ('Pepsi'), ('Ford'),
                              ('General Electric'), ('IBM'), ('McDonald''s'), ('Walmart'), ('Honda'),
                              ('Louis Vuitton'), ('Intel'), ('Nissan'), ('L''Oréal'), ('Audi'),
                              ('HP'), ('Canon'), ('Dell'), ('Panasonic');
INSERT INTO categorias (nome) VALUES
                                  ('Limpeza'), ('Eletrônicos'), ('Alimentos'), ('Roupas'), ('Calçados'),
                                  ('Automóveis'), ('Beleza'), ('Saúde'), ('Esportes'), ('Jogos'),
                                  ('Casa e Jardim'), ('Animais de Estimação'), ('Móveis'), ('Livros'), ('Filmes e Séries'),
                                  ('Música'), ('Arte e Artesanato'), ('Viagens'), ('Tecnologia'), ('Jóias'),
                                  ('Brinquedos'), ('Instrumentos Musicais'), ('Fotografia'), ('Eletrodomésticos'), ('Fitness'),
                                  ('Crianças e Bebês'), ('Papelaria'), ('Ferramentas'), ('Moda'), ('Acessórios');

INSERT INTO produtos (nome, descricao, precobase, idmarca) VALUES
            ('Nestle Chocolate', 'Barra de chocolate delicioso', 9.99, 1),
            ('Samsung 4K Smart TV', 'Televisão inteligente com resolução 4K', 3499.99, 4),
            ('Nike Air Max', 'Tênis esportivo para corrida', 299.99, 9),
            ('Amazon Echo Dot', 'Assistente de voz inteligente com Alexa', 199.99, 7),
            ('Coca-Cola Zero', 'Refrigerante sem açúcar', 5.49, 6),
            ('Adidas Originals Hoodie', 'Moletom com capuz para estilo casual', 79.99, 10),
            ('Toyota Camry', 'Carro sedan eficiente em combustível', 89999.99, 5),
            ('Samsung Galaxy Buds Pro', 'Fones de ouvido sem fio com cancelamento de ruído', 249.99, 4),
            ('L''Oréal Paris Shampoo', 'Shampoo para cabelos saudáveis', 14.99, 23),
            ('Microsoft Surface Laptop', 'Laptop fino e leve para produtividade', 12999.99, 3),
            ('McDonald''s Big Mac Combo', 'Combo de hambúrguer, batata frita e refrigerante', 9.99, 18),
            ('Sony PlayStation 5', 'Console de videogame de última geração', 499.99, 8),
            ('Honda Civic', 'Carro compacto e econômico', 21999.99, 20),
            ('Apple AirPods Pro', 'Fones de ouvido sem fio com cancelamento de ruído', 249.99, 2),
            ('Nestle Nescau', 'Chocolate em pó para bebidas quentes', 7.99, 1),
            ('Samsung Galaxy Tab S7', 'Tablet Android com tela Super AMOLED', 649.99, 4),
            ('Amazon Kindle Paperwhite', 'Leitor de e-books à prova d''água', 129.99, 7),
            ('Nike Dri-FIT Camiseta', 'Camiseta esportiva para corrida', 34.99, 9),
            ('Coca-Cola Classic', 'Refrigerante clássico com gás', 5.49, 6),
            ('Apple Watch Series 7', 'Relógio inteligente com monitoramento de saúde', 399.99, 2);


INSERT INTO produto_categoria (idproduto, idcategoria) VALUES
                                                           (1, 3), -- Nestle Chocolate - Alimentos
                                                           (2, 2), -- Samsung 4K Smart TV - Eletrônicos
                                                           (3, 4), -- Nike Air Max - Calçados
                                                           (3, 9), -- Nike Air Max - Esportes
                                                           (4, 2), -- Amazon Echo Dot - Eletrônicos
                                                           (4, 20), -- Amazon Echo Dot - Tecnologia
                                                           (5, 3), -- Coca-Cola Zero - Alimentos
                                                           (5, 6), -- Coca-Cola Zero - Bebidas
                                                           (6, 5), -- Adidas Originals Hoodie - Roupas
                                                           (7, 6), -- Toyota Camry - Automóveis
                                                           (8, 2), -- Samsung Galaxy Buds Pro - Eletrônicos
                                                           (8, 20), -- Samsung Galaxy Buds Pro - Tecnologia
                                                           (9, 7), -- L'Oréal Paris Shampoo - Beleza
                                                           (10, 2), -- Microsoft Surface Laptop - Eletrônicos
                                                           (10, 20), -- Microsoft Surface Laptop - Tecnologia
                                                           (11, 3), -- McDonald's Big Mac Combo - Alimentos
                                                           (11, 6), -- McDonald's Big Mac Combo - Bebidas
                                                           (12, 2), -- Sony PlayStation 5 - Eletrônicos
                                                           (12, 10), -- Sony PlayStation 5 - Jogos
                                                           (13, 6), -- Honda Civic - Automóveis
                                                           (14, 2), -- Apple AirPods Pro - Eletrônicos
                                                           (14, 20), -- Apple AirPods Pro - Tecnologia
                                                           (15, 3), -- Nestle Nescau - Alimentos
                                                           (15, 6), -- Nestle Nescau - Bebidas
                                                           (16, 2), -- Samsung Galaxy Tab S7 - Eletrônicos
                                                           (16, 20), -- Samsung Galaxy Tab S7 - Tecnologia
                                                           (17, 2), -- Amazon Kindle Paperwhite - Eletrônicos
                                                           (17, 15), -- Amazon Kindle Paperwhite - Livros
                                                           (18, 5), -- Nike Dri-FIT Camiseta - Roupas
                                                           (18, 9), -- Nike Dri-FIT Camiseta - Esportes
                                                           (19, 3), -- Coca-Cola Classic - Alimentos
                                                           (19, 6), -- Coca-Cola Classic - Bebidas
                                                           (20, 2), -- Apple Watch Series 7 - Eletrônicos
                                                           (20, 8); -- Apple Watch Series 7 - Saúde
