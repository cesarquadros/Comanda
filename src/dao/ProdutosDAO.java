package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

import model.Produtos;

public class ProdutosDAO extends Conexao{
	private Connection con;
	private Statement statement;
	private ResultSet rs;
	private String sql;
	private DecimalFormat df = new DecimalFormat("0.00");
	
	
	public boolean inserirProduto(Produtos produto) throws SQLException{
		
		try {
			con = abreConexao();
			statement = con.createStatement();
			
			sql = "INSERT INTO PRODUTOS(COD_CATEGORIA, DESCRICAO, PRECO, OBSERVACOES) "
					+ "VALUES('"+produto.getCodCategoria()+"','"+produto.getDescricao()+"','"+produto.getPreco()+"','"+produto.getObservacoes()+"')";
			statement.executeUpdate(sql);			
			con.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		con.close();
		return false;
	}
}
