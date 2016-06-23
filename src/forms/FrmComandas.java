package forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import dao.ComandaDAO;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;

public class FrmComandas {

	private JFrame formComandas;
	private JScrollPane scrollComanda;
	private JTable tabelaComandas;
	private JTable tabelaItensComanda;
	private JScrollPane scrollItensComanda;
	private JLabel lblNewLabel;
	private JTextField txtValorTotal;
	private JLabel lblComanda;
	private JTextField txtComanda;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmComandas window = new FrmComandas();
					window.formComandas.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the application.
	 */
	public FrmComandas() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		formComandas = new JFrame();
		formComandas.setBounds(100, 100, 922, 631);
		formComandas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		formComandas.getContentPane().setLayout(null);
		
		//Criando o Scroll e colocando a tabela dentro
		tabelaComandas = new JTable(0, 0);
		tabelaComandas.setBounds(50, 50, 50, 50);
		tabelaComandas.setSurrendersFocusOnKeystroke(true);
		tabelaComandas.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Comanda", "Nome", "Status"}) {
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		});
		scrollComanda = new JScrollPane(tabelaComandas);
		scrollComanda.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollComanda.setBounds(10, 105, 307, 437);
		formComandas.getContentPane().add(scrollComanda);
		
		
		tabelaItensComanda = new JTable(0, 0);
		tabelaItensComanda.setBounds(50, 50, 50, 50);
		tabelaItensComanda.setSurrendersFocusOnKeystroke(true);
		tabelaItensComanda.setModel(new DefaultTableModel(new Object[][] {}, new String[] {"Cliente", "Produto","Categoria","Observação","Preco"}) {
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		});
		scrollComanda = new JScrollPane(tabelaItensComanda);
		scrollComanda.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollComanda.setBounds(10, 25, 307, 437);
		scrollItensComanda = new JScrollPane(tabelaItensComanda);
		scrollItensComanda.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollItensComanda.setBounds(327, 105, 556, 388);
		formComandas.getContentPane().add(scrollItensComanda);
		

		
		lblNewLabel = new JLabel("Valor total");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		lblNewLabel.setBounds(337, 506, 121, 33);
		formComandas.getContentPane().add(lblNewLabel);
		
		txtValorTotal = new JTextField();
		txtValorTotal.setEditable(false);
		txtValorTotal.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		txtValorTotal.setBounds(462, 503, 103, 39);
		formComandas.getContentPane().add(txtValorTotal);
		txtValorTotal.setColumns(10);
		
		ComandaDAO comandaDAO = new ComandaDAO();
		comandaDAO.teste(tabelaItensComanda,txtValorTotal);
		
		lblComanda = new JLabel("Comanda");
		lblComanda.setForeground(Color.RED);
		lblComanda.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		lblComanda.setBounds(337, 57, 108, 33);
		formComandas.getContentPane().add(lblComanda);
		
		txtComanda = new JTextField();
		txtComanda.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		txtComanda.setEditable(false);
		txtComanda.setColumns(10);
		txtComanda.setBounds(452, 55, 150, 39);
		formComandas.getContentPane().add(txtComanda);

	}
}
