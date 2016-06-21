DROP TABLE IF EXISTS PRODUTOS;
DROP TABLE IF EXISTS COMANDA;
DROP TABLE IF EXISTS ITENS_COMANDA;
DROP TABLE IF EXISTS CATEGORIAS;

CREATE TABLE CATEGORIAS
(
	COD_CATEGORIA		INT IDENTITY(1,1) NOT NULL,
	CATEGORIA			VARCHAR(50) NOT NULL,	
	CONSTRAINT [PK_CATEGORIAS] PRIMARY KEY (COD_CATEGORIA)
);
INSERT INTO CATEGORIAS(CATEGORIA)VALUES ('BEBIDAS');
INSERT INTO CATEGORIAS(CATEGORIA)VALUES ('CERVEJA');
INSERT INTO CATEGORIAS(CATEGORIA)VALUES ('OUTROS');


CREATE TABLE PRODUTOS
(
	COD_PRODUTO		INT IDENTITY(1,1) NOT NULL,
	COD_CATEGORIA	INT NOT NULL,
	DESCRICAO			VARCHAR (50) NOT NULL,
	PRECO					VARCHAR (10) NOT NULL,
	CONSTRAINT [PK_PRODUTOS] PRIMARY KEY (COD_PRODUTO),
	CONSTRAINT FK_CATEGORIA FOREIGN KEY (COD_CATEGORIA) REFERENCES CATEGORIAS(COD_CATEGORIA)
);
INSERT INTO PRODUTOS(COD_CATEGORIA, DESCRICAO, PRECO) VALUES('2','SKOL','2,50');
INSERT INTO PRODUTOS(COD_CATEGORIA, DESCRICAO, PRECO) VALUES('2','BRAHMA','2,00');
INSERT INTO PRODUTOS(COD_CATEGORIA, DESCRICAO, PRECO) VALUES('3','CAMPARI','8.00');
INSERT INTO PRODUTOS(COD_CATEGORIA, DESCRICAO, PRECO) VALUES('1','AGUA','5,00');

CREATE TABLE COMANDA
(
	COD_COMANDA		INT IDENTITY(1,1) NOT NULL,
	NOME_CLIENTE	VARCHAR(50) NOT NULL,
	DATA_ATUAL		DATE NOT NULL,
	STATUS			VARCHAR(12) NOT NULL,
	VALOR_TOTAL		VARCHAR (10),
	CONSTRAINT [PK_COMANDA] PRIMARY KEY (COD_COMANDA) 
);
INSERT INTO COMANDA(NOME_CLIENTE, DATA_ATUAL, STATUS) VALUES('CESAR','21/06/2016','ABERTO'); 
INSERT INTO COMANDA(NOME_CLIENTE, DATA_ATUAL, STATUS) VALUES('JUBILA','21/06/2016','ABERTO');

CREATE TABLE ITENS_COMANDA
(
	COD_ITEM				INT IDENTITY(1,1) NOT NULL,
	COD_PRODUTO		INT NOT NULL,
	COD_COMANDA		INT NOT NULL,
	CONSTRAINT PK_ITENS_COMANDA PRIMARY KEY (COD_ITEM),
	CONSTRAINT FK_PRODUTO FOREIGN KEY (COD_PRODUTO) REFERENCES PRODUTOS(COD_PRODUTO),
	CONSTRAINT FK_COMANDA FOREIGN KEY (COD_COMANDA) REFERENCES COMANDA(COD_COMANDA)
	
);
INSERT INTO ITENS_COMANDA(COD_PRODUTO, COD_COMANDA)VALUES('1','1');
INSERT INTO ITENS_COMANDA(COD_PRODUTO, COD_COMANDA)VALUES('3','1');
INSERT INTO ITENS_COMANDA(COD_PRODUTO, COD_COMANDA)VALUES('4','2');
INSERT INTO ITENS_COMANDA(COD_PRODUTO, COD_COMANDA)VALUES('3','2');


SELECT C.NOME_CLIENTE AS CLIENTE, CA.CATEGORIA,P.DESCRICAO AS PRODUTOS, P.PRECO
FROM ITENS_COMANDA I
INNER JOIN COMANDA C ON C.COD_COMANDA = I.COD_COMANDA
INNER JOIN PRODUTOS P ON P.COD_PRODUTO = I.COD_PRODUTO
INNER JOIN CATEGORIAS CA ON CA.COD_CATEGORIA = P.COD_CATEGORIA
WHERE I.COD_COMANDA='2'

SELECT * FROM PRODUTOS