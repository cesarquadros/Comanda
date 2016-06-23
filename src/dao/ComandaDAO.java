package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.Conexao;

public class ComandaDAO extends Conexao {
	private Connection con;
	private Statement statement;
	private ResultSet rs;

	public void teste(JTable tabela, JTextField valorTotal1) {
		DefaultTableModel model = (DefaultTableModel) tabela.getModel();
		DecimalFormat df = new DecimalFormat("00.00");
		try {
			con = abreConexao();
			statement = con.createStatement();

			String sql = "SELECT C.COD_COMANDA AS COMANDA, C.NOME_CLIENTE AS CLIENTE, P.DESCRICAO AS PRODUTOS, P.OBSERVACOES, CA.CATEGORIA, P.PRECO"
					+ " FROM ITENS_COMANDA I" + " INNER JOIN COMANDA C ON C.COD_COMANDA = I.COD_COMANDA"
					+ " INNER JOIN PRODUTOS P ON P.COD_PRODUTO = I.COD_PRODUTO"
					+ " INNER JOIN CATEGORIAS CA ON CA.COD_CATEGORIA = P.COD_CATEGORIA"
					+ " WHERE I.COD_COMANDA='1000'";

			rs = statement.executeQuery(sql);

			int linhas = model.getRowCount();
			for (int i = 0; i < linhas; i++) {
				model.removeRow(0);
			}
			float valorTotal = 0;
			while (rs.next()) {
				model.addRow(new String[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6) });
				valorTotal = valorTotal + rs.getFloat(6);
			}

			valorTotal1.setText("R$"+df.format(valorTotal));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
