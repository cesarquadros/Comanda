package dao;

import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.Conexao;
import model.Comanda;
import model.Pagamento;

public class ComandaDAO extends Conexao {
	private Connection con;
	private Statement statement;
	private ResultSet rs;
	private String sql;
	private DecimalFormat df = new DecimalFormat("0.00");

	
    public boolean abrirComanda(Comanda comanda) {
        try {
            con = abreConexao();
            sql = "INSERT INTO COMANDA(NOME_CLIENTE, DATA_INICIO, STATUS) VALUES('"+comanda.getNome()+"','"+comanda.getData()+"','"+comanda.getStatus()+"')";
            statement = con.createStatement();
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

	public void atualizarComandas(JTable tabelaComanda, JLabel lblQtdComandas) throws SQLException {
		DefaultTableModel model = (DefaultTableModel) tabelaComanda.getModel();
		limparTabela(tabelaComanda);
		try {
			con = abreConexao();
			statement = con.createStatement();

			String sql = "SELECT COD_COMANDA, NOME_CLIENTE, STATUS, CONVERT(VARCHAR(10),DATA_INICIO,103) FROM COMANDA WHERE STATUS = 'ABERTO'";
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

	public void fecharComanda(String numeroComanda, String status) throws SQLException {
		try {
			con = abreConexao();
			statement = con.createStatement();
			String sql = "UPDATE COMANDA SET STATUS ='" + status + "' WHERE COD_COMANDA = '" + numeroComanda + "'";
			statement.executeUpdate(sql);
			
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		con.close();
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

	public float valorAPagar(int codComanda) throws SQLException {
		float valorApagar = 0;
		try {
			con = abreConexao();
			statement = con.createStatement();
			sql = "SELECT VALOR_PAGO FROM PAGAMENTOS WHERE COD_COMANDA ='" + codComanda + "'";
			rs = statement.executeQuery(sql);

			while (rs.next()) {
				valorApagar += rs.getFloat(1);
			}
			con.close();
			return valorApagar;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		con.close();
		return valorApagar;
	}
	
	public boolean efetuarPagamento(Pagamento pagamento) throws SQLException{
		
		try {
			con = abreConexao();
			statement = con.createStatement();
			
			sql = "INSERT INTO PAGAMENTOS(COD_COMANDA, PAG_OBSERVACOES, VALOR_PAGO)"
					+ "VALUES('"+pagamento.getCodComandao()+"','"+pagamento.getObservacaoPagamento()+"','"+pagamento.getValorPagamento()+"')";
			statement.executeUpdate(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			con.close();
			return false;
		}		
		con.close();
		return true;
	}

	public ArrayList<Pagamento> historicoPagamentos(int codComanda, JTextArea textComprovante) throws SQLException{
		ArrayList<Pagamento> arrayPagamento = new ArrayList<Pagamento>();
		
		try {
			con = abreConexao();
			statement = con.createStatement();
			
			sql = "SELECT PAG_OBSERVACOES, VALOR_PAGO FROM PAGAMENTOS WHERE COD_COMANDA = '"+codComanda+"'";
			rs = statement.executeQuery(sql);
			
			while(rs.next()){
				Pagamento pagamento = new Pagamento();
				pagamento.setObservacaoPagamento(rs.getString(1));
				pagamento.setValorPagamento(rs.getFloat(2));
				arrayPagamento.add(pagamento);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			con.close();
		}
		con.close();
		return arrayPagamento;
	}
	
	public void comprovante(JTextArea textComprovante, int codComanda) throws SQLException{
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
		String dataAtual = dateFormat.format(new Date());
		try {
			con = abreConexao();
			statement = con.createStatement();
			
			sql = "SELECT"+
					" P.DESCRICAO,P.PRECO, COUNT(*) AS CONT"+
					" FROM " + 
					" ITENS_COMANDA IC, PRODUTOS P, CATEGORIAS CA"+
					" WHERE"+ 
					" P.COD_PRODUTO = IC.COD_PRODUTO"+ 
					" AND"+ 
					" P.COD_CATEGORIA = CA.COD_CATEGORIA"+
					" AND"+
					" IC.COD_COMANDA = '"+codComanda+"'"+
					" GROUP BY"+ 
					" P.DESCRICAO, P.PRECO";			
			rs = statement.executeQuery(sql);
			
			textComprovante.setText(null);
			textComprovante.setText(textComprovante.getText() + "               BAR DO BUGÃO" + System.lineSeparator());
			textComprovante.setText(textComprovante.getText() +"         "+dataAtual + System.lineSeparator());
			textComprovante.setText(textComprovante.getText() + "               CUPOM FISCAL" + System.lineSeparator());
			textComprovante.setText(textComprovante.getText() +System.lineSeparator());
			textComprovante.setText(textComprovante.getText()+" -------------  CONSUMO  -------------" +System.lineSeparator());	
			textComprovante.setText(textComprovante.getText() +System.lineSeparator());

			while(rs.next()){
				
				textComprovante.setText(textComprovante.getText() +" "+ rs.getString(1) + "   x"+ rs.getInt(3)+ "   R$"+ df.format(rs.getFloat(2)* rs.getInt(3))+ System.lineSeparator());
			}

			textComprovante.setText(textComprovante.getText() +System.lineSeparator());	
			textComprovante.setText(textComprovante.getText()+ "---- HISTÓRICO DE PAGAMENTOS ----"+System.lineSeparator());
			textComprovante.setText(textComprovante.getText() +System.lineSeparator());
			
			ArrayList<Pagamento> arrayPag = new ArrayList<Pagamento>();
			arrayPag = historicoPagamentos(codComanda, textComprovante);
			
			for (int i = 0; i < arrayPag.size(); i++) {
				textComprovante.setText(textComprovante.getText()+" "+arrayPag.get(i).getObservacaoPagamento()+" --- R$"+ df.format(arrayPag.get(i).getValorPagamento())+ System.lineSeparator());				
			}
			
			textComprovante.setText(textComprovante.getText()+" ------------------------------------------- " +System.lineSeparator());
			textComprovante.setText(textComprovante.getText() +System.lineSeparator());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			con.close();
		}		
		con.close();
	}
}
