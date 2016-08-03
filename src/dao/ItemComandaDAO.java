package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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

	public void atualizarItensComanda(JTable tabelaItensComanda, int numeroComanda) throws SQLException {
		DefaultTableModel model = (DefaultTableModel) tabelaItensComanda.getModel();
		DecimalFormat df = new DecimalFormat("0.00");
		float valorTotal = 0;
		try {
			con = abreConexao();
			statement = con.createStatement();

			sql = "SELECT C.NOME_CLIENTE AS CLIENTE, P.DESCRICAO AS PRODUTOS, P.OBSERVACOES, CA.CATEGORIA, P.PRECO, I.COD_ITEM"
					+ " FROM ITENS_COMANDA I" + " INNER JOIN COMANDA C ON C.COD_COMANDA = I.COD_COMANDA"
					+ " INNER JOIN PRODUTOS P ON P.COD_PRODUTO = I.COD_PRODUTO"
					+ " INNER JOIN CATEGORIAS CA ON CA.COD_CATEGORIA = P.COD_CATEGORIA" + " WHERE I.COD_COMANDA='"
					+ numeroComanda + "'";

			rs = statement.executeQuery(sql);

			limparTabela(tabelaItensComanda);
			
			while (rs.next()) {
				model.addRow(new String[] { rs.getString(1), rs.getString(2), rs.getString(4), rs.getString(3),
						"R$"+rs.getString(5), rs.getString(6) });
				valorTotal = valorTotal + rs.getFloat(5);
			}
			con.close();
			//return valorTotal;

			//txtValorTotal.setText("R$" + df.format(valorTotal));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			con.close();
			e.printStackTrace();
		}
		con.close();
		//return valorTotal;
	}
	
	public float valorTotal(int numeroComanda) throws SQLException {
		DecimalFormat df = new DecimalFormat("0.00");
		float valorTotal = 0;
		try {
			con = abreConexao();
			statement = con.createStatement();

			sql = "SELECT P.PRECO "
					+ "FROM PRODUTOS P "
					+ "INNER JOIN ITENS_COMANDA I ON I.COD_PRODUTO = P.COD_PRODUTO "
					+ "WHERE I.COD_COMANDA = '"+ numeroComanda + "'";

			rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				valorTotal = valorTotal + rs.getFloat(1);
			}
			con.close();
			return valorTotal;

			//txtValorTotal.setText("R$" + df.format(valorTotal));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			con.close();
			e.printStackTrace();
		}
		con.close();
		return valorTotal;
	}
	
	public void limparTabela(JTable tabela) {
		DefaultTableModel model = (DefaultTableModel) tabela.getModel();
		int linhas = model.getRowCount();
		for (int i = 0; i < linhas; i++) {
			model.removeRow(0);
		}
	}
}
