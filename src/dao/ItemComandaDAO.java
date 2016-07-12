package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ItemComandaDAO extends Conexao{
	private Connection con;
	private Statement statement;
	private ResultSet rs;
	private String sql;
	
	public boolean excluirItemComanda(int codItem){
		try {
			con = abreConexao();
			statement = con.createStatement();
			
			sql = "DELETE FROM ITENS_COMANDA WHERE COD_ITEM = '"+codItem+"'";
			statement.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
