

SELECT * FROM CATEGORIAS;
SELECT * FROM PRODUTOS;
SELECT * FROM COMANDA;
SELECT * FROM ITENS_COMANDA;

SELECT C.NOME_CLIENTE AS CLIENTE, CA.CATEGORIA,P.DESCRICAO AS PRODUTOS, P.PRECO
FROM ITENS_COMANDA I
INNER JOIN COMANDA C ON C.COD_COMANDA = I.COD_COMANDA
INNER JOIN PRODUTOS P ON P.COD_PRODUTO = I.COD_PRODUTO
INNER JOIN CATEGORIAS CA ON CA.COD_CATEGORIA = P.COD_CATEGORIA
WHERE I.COD_COMANDA='2'

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