package model;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import dao.ComandaDAO;
import dao.ItemComandaDAO;

public class Pagamento extends ComandaDAO {
	private int codComanda;
	private float valorPagamento = 0;
	private String observacaoPagamento ="";
	
	public Pagamento(){
	}
	
	public Pagamento(int codComanda, float valorPagamento, String obervacaoPagamento){
		this.codComanda = codComanda;
		this.valorPagamento = valorPagamento;
		this.observacaoPagamento = obervacaoPagamento;
	}
	
	public int getCodComandao() {
		return codComanda;
	}
	public void setCodComanda(int codPagamento) {
		this.codComanda = codPagamento;
	}
	public float getValorPagamento() {
		return valorPagamento;
	}
	public void setValorPagamento(float valorPagamento) {
		this.valorPagamento = valorPagamento;
	}
	public String getObservacaoPagamento() {
		return observacaoPagamento;
	}
	public void setObservacaoPagamento(String observacaoPagamento) {
		this.observacaoPagamento = observacaoPagamento;
	}
	
	public boolean efetuarPagamentos(JTable tabelaComandas) throws SQLException{
		valorPagamento = 0;
		observacaoPagamento ="";

		while (observacaoPagamento.equals("")) {
			observacaoPagamento = JOptionPane.showInputDialog("Observação", null);
		}

		while (valorPagamento <= 0) {
			try {
				valorPagamento = Float.parseFloat(
						JOptionPane.showInputDialog("Digite o valor a ser pago", null).replaceAll(",", "."));
				
			} catch (NumberFormatException e) {
				System.out.println(e);
				JOptionPane.showMessageDialog(null, "Digite apenas números", "Bar do Bugão",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		codComanda = Integer
				.parseInt((String) tabelaComandas.getModel().getValueAt(tabelaComandas.getSelectedRow(), 0));

		ItemComandaDAO itemComandaDAO = new ItemComandaDAO();
		float valorTotal = itemComandaDAO.valorTotal(codComanda);
		float valorPago = valorAPagar(codComanda);
		float valorRestante = valorTotal - valorPago;
		
		if(valorRestante<valorPagamento){
			JOptionPane.showMessageDialog(null, "Valor inserido maior que o total da conta", "Bar do Bugão",
					JOptionPane.ERROR_MESSAGE);
		}else{
			Pagamento pagamento = new Pagamento(codComanda, valorPagamento, observacaoPagamento);
			return efetuarPagamento(pagamento);
		}
		return false;
	}

}
