package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class RelatorioDAO extends Conexao{
	
	private Connection con;
	private Statement statement;
	private ResultSet rs;
	private String sql;
	private DecimalFormat df = new DecimalFormat("0.00");
	
	public void atualizarComandas(JTable tabelaComanda, JLabel lblQtdComandas) throws SQLException {
		DefaultTableModel model = (DefaultTableModel) tabelaComanda.getModel();
		limparTabela(tabelaComanda);
		try {
			con = abreConexao();
			statement = con.createStatement();
			String sql = "SELECT COD_COMANDA, NOME_CLIENTE, STATUS, CONVERT(VARCHAR(10),DATA_INICIO,103) FROM COMANDA";
			rs = statement.executeQuery(sql);
			int qtdComandas = 0;
			while (rs.next()) {
				model.addRow(new String[] { rs.getString("COD_COMANDA"), rs.getString("NOME_CLIENTE"),
						rs.getString("STATUS"), rs.getString(4) });
				qtdComandas++;
			}
			String quantidadeCom = String.valueOf(qtdComandas);
			lblQtdComandas.setText(quantidadeCom);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		con.close();
	}
	
	public void limparTabela(JTable tabela) {
		DefaultTableModel model = (DefaultTableModel) tabela.getModel();
		int linhas = model.getRowCount();
		for (int i = 0; i < linhas; i++) {
			model.removeRow(0);
		}
	}
	
}
