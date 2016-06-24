package dao;

import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.Conexao;

public class ComandaDAO extends Conexao {
	private Connection con;
	private Statement statement;
	private ResultSet rs;

	public void atualizarItensComanda(JTable tabelaItensComanda, JTextField txtValorTotal, String numeroComanda) {
		DefaultTableModel model = (DefaultTableModel) tabelaItensComanda.getModel();
		DecimalFormat df = new DecimalFormat("00.00");
		try {
			con = abreConexao();
			statement = con.createStatement();

			String sql = "SELECT C.NOME_CLIENTE AS CLIENTE, P.DESCRICAO AS PRODUTOS, P.OBSERVACOES, CA.CATEGORIA, P.PRECO"
					+ " FROM ITENS_COMANDA I" + " INNER JOIN COMANDA C ON C.COD_COMANDA = I.COD_COMANDA"
					+ " INNER JOIN PRODUTOS P ON P.COD_PRODUTO = I.COD_PRODUTO"
					+ " INNER JOIN CATEGORIAS CA ON CA.COD_CATEGORIA = P.COD_CATEGORIA" + " WHERE I.COD_COMANDA='"
					+ numeroComanda + "'";

			rs = statement.executeQuery(sql);

			limparTabela(tabelaItensComanda);
			float valorTotal = 0;
			while (rs.next()) {
				model.addRow(new String[] { rs.getString(1), rs.getString(2), rs.getString(4), rs.getString(3),
						rs.getString(5) });
				valorTotal = valorTotal + rs.getFloat(5);
			}

			txtValorTotal.setText("R$" + df.format(valorTotal));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void atualizarComandas(JTable tabelaComanda, JLabel lblQtdComandas) {
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

	}

	public void fecharComanda(String numeroComanda, String status) {
		try {
			con = abreConexao();
			statement = con.createStatement();
			String sql = "UPDATE COMANDA SET STATUS ='" + status + "' WHERE COD_COMANDA = '" + numeroComanda + "'";

			statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static JTable getNewRenderedTable(final JTable table) {
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int col) {
				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

				String status = (String) table.getModel().getValueAt(row, 2);

				if (status.equalsIgnoreCase("ABERTO")) {
					setForeground(new Color(34, 139, 34));
				} else if (status.equalsIgnoreCase("FECHADO")) {
					setForeground(Color.red);
				}

				return this;
			}
		});
		return table;
	}

	public void limparTabela(JTable tabela) {
		DefaultTableModel model = (DefaultTableModel) tabela.getModel();
		int linhas = model.getRowCount();
		for (int i = 0; i < linhas; i++) {
			model.removeRow(0);
		}
	}
}
