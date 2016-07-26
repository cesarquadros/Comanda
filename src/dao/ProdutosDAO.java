package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
	
	public void preecherTabela(JTable tabelaProdutos){
		DefaultTableModel model = (DefaultTableModel) tabelaProdutos.getModel();
		int linhas = model.getRowCount();
		for(int i = 0; i<linhas; i++){
			model.removeRow(0);
		}
		
		try {
			con = abreConexao();
			statement = con.createStatement();
			
			sql = "SELECT COD_PRODUTO,DESCRICAO, CATEGORIA, OBSERVACOES, PRECO "
					+ "FROM PRODUTOS P "
					+ "INNER JOIN CATEGORIAS C ON P.COD_CATEGORIA = C.COD_CATEGORIA";
			rs = statement.executeQuery(sql);
			

			
			while (rs.next()) {
				model.addRow(new String[]{rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)});
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
