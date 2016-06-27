
DROP TABLE PAGAMENTOS;
DROP TABLE ITENS_COMANDA;
DROP TABLE COMANDA;
DROP TABLE PRODUTOS;
DROP TABLE CATEGORIAS;

CREATE TABLE CATEGORIAS
(
	COD_CATEGORIA		INT IDENTITY(1,1) NOT NULL,
	CATEGORIA			VARCHAR(50) NOT NULL,	
	CONSTRAINT [PK_CATEGORIAS] PRIMARY KEY (COD_CATEGORIA)
);

CREATE TABLE PRODUTOS
(
	COD_PRODUTO		INT IDENTITY(100,1) NOT NULL,
	COD_CATEGORIA	INT NOT NULL,
	DESCRICAO		VARCHAR (50) NOT NULL,
	PRECO			DECIMAL (5,2) NOT NULL,
	OBSERVACOES		VARCHAR (50),
	CONSTRAINT [PK_PRODUTOS] PRIMARY KEY (COD_PRODUTO),
	CONSTRAINT FK_CATEGORIA FOREIGN KEY (COD_CATEGORIA) REFERENCES CATEGORIAS(COD_CATEGORIA)
);


CREATE TABLE COMANDA
(
	COD_COMANDA		INT IDENTITY(1000,1) NOT NULL,
	NOME_CLIENTE	VARCHAR(50) NOT NULL,
	DATA_INICIO		DATE NOT NULL,
	DATA_FINAL		DATE,
	STATUS			VARCHAR(12) NOT NULL,
	VALOR_TOTAL		DECIMAL (5,2),
	CONSTRAINT [PK_COMANDA] PRIMARY KEY (COD_COMANDA) 
);

CREATE TABLE ITENS_COMANDA
(
	COD_ITEM		INT IDENTITY(1,1) NOT NULL,
	COD_PRODUTO		INT NOT NULL,
	COD_COMANDA		INT NOT NULL,
	OBSERVACOES		VARCHAR (50),
	CONSTRAINT PK_ITENS_COMANDA PRIMARY KEY (COD_ITEM),
	CONSTRAINT FK_PRODUTO FOREIGN KEY (COD_PRODUTO) REFERENCES PRODUTOS(COD_PRODUTO),
	CONSTRAINT FK_COMANDA FOREIGN KEY (COD_COMANDA) REFERENCES COMANDA(COD_COMANDA)
	
);

CREATE TABLE PAGAMENTOS
(
	COD_PAGAMENTO	INT IDENTITY(1,1) NOT NULL,
	COD_COMANDA		INT NOT NULL,
	PAG_OBSERVACOES	VARCHAR (100),
	VALOR_PAGO		DECIMAL (5,2),
	CONSTRAINT PK_PAGAMENTOS PRIMARY KEY (COD_PAGAMENTO),
	CONSTRAINT FK_COMANDA2 FOREIGN KEY (COD_COMANDA) REFERENCES COMANDA(COD_COMANDA)
);
--------------------------------------------------------------------------------------------------------------------------------------------------

INSERT INTO CATEGORIAS(CATEGORIA)VALUES ('BEBIDAS'); --1
INSERT INTO CATEGORIAS(CATEGORIA)VALUES ('CERVEJA'); --2
INSERT INTO CATEGORIAS(CATEGORIA)VALUES ('OUTROS'); --3
INSERT INTO CATEGORIAS(CATEGORIA)VALUES ('CALDOS'); --4

INSERT INTO PRODUTOS(COD_CATEGORIA, DESCRICAO, PRECO, OBSERVACOES) VALUES('1','AGUA','5.00','500ML'); --1
INSERT INTO PRODUTOS(COD_CATEGORIA, DESCRICAO, PRECO, OBSERVACOES) VALUES('1','COCA-COLA','4.00','LATA'); --2
INSERT INTO PRODUTOS(COD_CATEGORIA, DESCRICAO, PRECO, OBSERVACOES) VALUES('2','SKOL','2.50','239ML'); --3
INSERT INTO PRODUTOS(COD_CATEGORIA, DESCRICAO, PRECO, OBSERVACOES) VALUES('2','BRAHMA','4.00','350'); --4
INSERT INTO PRODUTOS(COD_CATEGORIA, DESCRICAO, PRECO, OBSERVACOES) VALUES('3','CAMPARI','8.00','DOSE'); --5
INSERT INTO PRODUTOS(COD_CATEGORIA, DESCRICAO, PRECO, OBSERVACOES) VALUES('3','VODKA','12.00','DOSE'); --6
INSERT INTO PRODUTOS(COD_CATEGORIA, DESCRICAO, PRECO) VALUES('4','VERDE','11.00'); --7
INSERT INTO PRODUTOS(COD_CATEGORIA, DESCRICAO, PRECO) VALUES('4','MOCOT�','9.00'); --8
INSERT INTO PRODUTOS(COD_CATEGORIA, DESCRICAO, PRECO) VALUES('4','GALINHA','6.00'); --9
INSERT INTO PRODUTOS(COD_CATEGORIA, DESCRICAO, PRECO) VALUES('4','PIRANHA','6.00'); --10

INSERT INTO COMANDA(NOME_CLIENTE, DATA_INICIO, STATUS) VALUES('CESAR','21/06/2016','ABERTO'); --1
INSERT INTO COMANDA(NOME_CLIENTE, DATA_INICIO, STATUS) VALUES('LILIAN','22/06/2016','ABERTO'); --2
INSERT INTO COMANDA(NOME_CLIENTE, DATA_INICIO, STATUS) VALUES('JO�O','23/06/2016','ABERTO'); --3

INSERT INTO PAGAMENTOS(COD_COMANDA, PAG_OBSERVACOES, VALOR_PAGO) VALUES('1000','ALGUEM PAGOU','5.00');
INSERT INTO PAGAMENTOS(COD_COMANDA, PAG_OBSERVACOES, VALOR_PAGO) VALUES('1001','ALGUEM PAGOU','8.00');
INSERT INTO PAGAMENTOS(COD_COMANDA, PAG_OBSERVACOES, VALOR_PAGO) VALUES('1002','ALGUEM PAGOU','7.40');

INSERT INTO ITENS_COMANDA(COD_PRODUTO, COD_COMANDA)VALUES('100','1000');
INSERT INTO ITENS_COMANDA(COD_PRODUTO, COD_COMANDA)VALUES('102','1000');
INSERT INTO ITENS_COMANDA(COD_PRODUTO, COD_COMANDA)VALUES('108','1000');
INSERT INTO ITENS_COMANDA(COD_PRODUTO, COD_COMANDA)VALUES('103','1001');
INSERT INTO ITENS_COMANDA(COD_PRODUTO, COD_COMANDA)VALUES('102','1001');
INSERT INTO ITENS_COMANDA(COD_PRODUTO, COD_COMANDA)VALUES('106','1001');
INSERT INTO ITENS_COMANDA(COD_PRODUTO, COD_COMANDA)VALUES('105','1002');
INSERT INTO ITENS_COMANDA(COD_PRODUTO, COD_COMANDA)VALUES('104','1002');
INSERT INTO ITENS_COMANDA(COD_PRODUTO, COD_COMANDA)VALUES('107','1002');
--------------------------------------------------------------------------------------------------------------------------------------------------
SELECT * FROM CATEGORIAS;
SELECT * FROM PRODUTOS;
SELECT * FROM COMANDA;
SELECT * FROM ITENS_COMANDA;
SELECT * FROM PAGAMENTOS;

SELECT C.COD_COMANDA AS COMANDA, C.NOME_CLIENTE AS CLIENTE, P.DESCRICAO AS PRODUTOS, P.OBSERVACOES, CA.CATEGORIA, P.PRECO
FROM ITENS_COMANDA I
INNER JOIN COMANDA C ON C.COD_COMANDA = I.COD_COMANDA
INNER JOIN PRODUTOS P ON P.COD_PRODUTO = I.COD_PRODUTO
INNER JOIN CATEGORIAS CA ON CA.COD_CATEGORIA = P.COD_CATEGORIA
WHERE I.COD_COMANDA='1000'

SELECT COD_CATEGORIA, COUNT(*)FROM PRODUTOS GROUP BY COD_CATEGORIA;

-- nome e quantidade de produtos separados por categorias
SELECT C.CATEGORIA,
	(SELECT COUNT(*) FROM PRODUTOS
	WHERE COD_CATEGORIA = C.COD_CATEGORIA)AS 'QUANTIDADE'
	FROM CATEGORIAS C;
	
SELECT I.COD_PRODUTO, P.PRECO
FROM ITENS_COMANDA I
INNER JOIN PRODUTOS P ON P.COD_PRODUTO = I.COD_PRODUTO
WHERE I.COD_COMANDA = '1'