package forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.sun.glass.ui.Pixels.Format;

import dao.ComandaDAO;
import model.ItemComanda;

import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

public class FrmComandas extends ComandaDAO {

	private JFrame formComandas;
	private JScrollPane scrollComanda;
	private JTable tabelaComandas;
	private static JTable tabelaItensComanda;
	private JScrollPane scrollItensComanda;
	private JLabel lblNewLabel;
	private JTextField txtValorTotal;
	private ItemComanda itemComanda = new ItemComanda();
	private JTextField txtValorPago;
	private JLabel lblValorPago;
	private JTextField txtValorRestante;
	private JLabel lblPagar;
	private DecimalFormat df = new DecimalFormat("0.00");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		/*
		 * tabelaItensComanda.getColumnModel().getColumn(0).setCellRenderer(new
		 * adicionarIcone((NewJFrame.class.getResource("/image/xis.png")).
		 * toString())); DefaultTableModel modelo = (DefaultTableModel)
		 * tabelaItensComanda.getModel(); modelo.addRow(new Object[]{null,
		 * "Descricao"});
		 */
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
		formComandas.getContentPane().setBackground(Color.WHITE);
		formComandas.setTitle("BAR DO BUG\u00C3O");
		formComandas.setBounds(100, 100, 1031, 696);
		formComandas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		formComandas.getContentPane().setLayout(null);

		// Criando o Scroll e colocando a tabela dentro
		tabelaComandas = new JTable(0, 0);
		tabelaComandas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabelaComandas.setBounds(50, 50, 50, 50);
		tabelaComandas.setSurrendersFocusOnKeystroke(true);
		tabelaComandas.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "Comanda", "Nome", "Status", "Data" }) {
					public boolean isCellEditable(int row, int col) {
						return false;
					}
				});
		scrollComanda = new JScrollPane(tabelaComandas);
		scrollComanda.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollComanda.setBounds(10, 144, 307, 487);
		formComandas.getContentPane().add(scrollComanda);

		tabelaItensComanda = new JTable(0, 0);
		tabelaItensComanda.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabelaItensComanda.setBounds(50, 50, 50, 50);
		tabelaItensComanda.setSurrendersFocusOnKeystroke(true);
		tabelaItensComanda.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Cliente", "Produto", "Categoria", "Observação", "Preco", "", "" }) {
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		});

		// Bloqueia a rendenização das tabelas
		tabelaItensComanda.getTableHeader().setResizingAllowed(false);
		// Bloqueia a reordenação das tabelas
		tabelaItensComanda.getTableHeader().setReorderingAllowed(false);
		tabelaItensComanda.getColumnModel().getColumn(6).setPreferredWidth(2);
		tabelaItensComanda.getColumnModel().getColumn(5).setMaxWidth(0);
		tabelaItensComanda.getColumnModel().getColumn(5).setMinWidth(0);
		tabelaItensComanda.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);

		scrollComanda = new JScrollPane(tabelaItensComanda);
		scrollComanda.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollComanda.setBounds(10, 25, 307, 437);
		scrollItensComanda = new JScrollPane(tabelaItensComanda);
		scrollItensComanda.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollItensComanda.setBounds(327, 131, 674, 388);
		formComandas.getContentPane().add(scrollItensComanda);

		lblNewLabel = new JLabel("Valor total");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		lblNewLabel.setBounds(327, 543, 88, 33);
		formComandas.getContentPane().add(lblNewLabel);

		txtValorTotal = new JTextField();
		txtValorTotal.setBackground(Color.WHITE);
		txtValorTotal.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtValorTotal.setEditable(false);
		txtValorTotal.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		txtValorTotal.setBounds(414, 548, 103, 26);
		formComandas.getContentPane().add(txtValorTotal);
		txtValorTotal.setColumns(10);

		JLabel lblQtdComandas = new JLabel("Comandas abertas: ");
		lblQtdComandas.setForeground(Color.BLACK);
		lblQtdComandas.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblQtdComandas.setBounds(10, 622, 115, 33);
		formComandas.getContentPane().add(lblQtdComandas);

		JLabel lblQuantidade = new JLabel("0");
		lblQuantidade.setForeground(Color.BLACK);
		lblQuantidade.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblQuantidade.setBounds(118, 625, 40, 26);
		formComandas.getContentPane().add(lblQuantidade);

		JButton btnFecharComanda = new JButton("Fechar a conta");
		btnFecharComanda.setEnabled(false);
		btnFecharComanda.setBounds(332, 590, 131, 41);
		formComandas.getContentPane().add(btnFecharComanda);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(FrmComandas.class.getResource("/imagens/logoNovo350.png")));
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		label.setBounds(370, -5, 350, 155);
		formComandas.getContentPane().add(label);

		txtValorPago = new JTextField();
		txtValorPago.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		txtValorPago.setEditable(false);
		txtValorPago.setColumns(10);
		txtValorPago.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtValorPago.setBackground(Color.WHITE);
		txtValorPago.setBounds(670, 545, 103, 26);
		formComandas.getContentPane().add(txtValorPago);

		lblValorPago = new JLabel("Valor pago");
		lblValorPago.setForeground(new Color(0, 128, 0));
		lblValorPago.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		lblValorPago.setBounds(580, 543, 88, 33);
		formComandas.getContentPane().add(lblValorPago);

		txtValorRestante = new JTextField();
		txtValorRestante.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		txtValorRestante.setEditable(false);
		txtValorRestante.setColumns(10);
		txtValorRestante.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtValorRestante.setBackground(Color.WHITE);
		txtValorRestante.setBounds(900, 545, 103, 26);
		formComandas.getContentPane().add(txtValorRestante);

		lblPagar = new JLabel("\u00C0 pagar");
		lblPagar.setForeground(new Color(0, 0, 255));
		lblPagar.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		lblPagar.setBounds(830, 543, 79, 33);
		formComandas.getContentPane().add(lblPagar);

		JButton btnEfetuarPagamento = new JButton("Efetuar pagamento");
		btnEfetuarPagamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				
				float valorPago = 0;

				while (valorPago <= 0) {
					try {
					valorPago = Float.parseFloat(JOptionPane.showInputDialog("Digite o valor a ser pago", null).replaceAll(",", "."));					
					valorPago = Float.valueOf(valorPago);
					} catch (NumberFormatException e) {
						System.out.println(e);
						JOptionPane.showMessageDialog(null, "Digite apenas números", "Bar do Bugão", JOptionPane.ERROR_MESSAGE);
					}					
				}
				int numeroComanda = Integer.parseInt((String) tabelaComandas.getModel().getValueAt(tabelaComandas.getSelectedRow(), 0));
				System.out.println("valor: "+ valorPago+ " comanda "+ numeroComanda );
				
			}
		});
		btnEfetuarPagamento.setEnabled(false);
		btnEfetuarPagamento.setBounds(489, 590, 144, 41);
		formComandas.getContentPane().add(btnEfetuarPagamento);

		btnFecharComanda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String numeroComanda = (String) tabelaComandas.getModel().getValueAt(tabelaComandas.getSelectedRow(),
						0);
				int confirmacao = JOptionPane.showConfirmDialog(null, "Deseja realmente fechar a conta?",
						"Bar do Bugão", JOptionPane.YES_NO_OPTION);
				if (confirmacao == JOptionPane.YES_OPTION) {
					fecharComanda(numeroComanda, "FECHADO");
					atualizarComandas(tabelaComandas, lblQuantidade);
					limparTabela(tabelaItensComanda);
					btnFecharComanda.setEnabled(false);
				} else {

				}

			}
		});

		tabelaComandas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				DefaultTableModel model = (DefaultTableModel) tabelaComandas.getModel();

				if (model.getRowCount() > 0) {
					if (tabelaComandas.getSelectedRow() >= 0) {
						int numeroComanda = Integer.parseInt(
								(String) tabelaComandas.getModel().getValueAt(tabelaComandas.getSelectedRow(), 0));
						float valorTotal = atualizarItensComanda(tabelaItensComanda, numeroComanda);
						float valorPago = valorAPagar(numeroComanda);
						float valorAPagar = valorTotal - valorPago;
						txtValorTotal.setText("R$" + df.format(valorTotal));
						txtValorPago.setText("R$" + df.format(valorPago));
						txtValorRestante.setText("R$" + df.format(valorAPagar));
						btnFecharComanda.setEnabled(true);
						btnEfetuarPagamento.setEnabled(true);
						tabelaItensComanda.getColumnModel().getColumn(6).setCellRenderer(
								new adicionarIcone((FrmComandas.class.getResource("/imagens/excluir.png")).toString()));

					}
				}
			}
		});

		tabelaItensComanda.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent clique) {
				if (clique.getClickCount() == 2) {

					itemComanda.excluirItemComanda(tabelaItensComanda);
					int numeroComanda = Integer.parseInt(
							(String) tabelaComandas.getModel().getValueAt(tabelaComandas.getSelectedRow(), 0));
					float valorTotal = atualizarItensComanda(tabelaItensComanda, numeroComanda);
					float valorPago = valorAPagar(numeroComanda);
					float valorAPagar = valorTotal - valorPago;
					txtValorTotal.setText("R$" + df.format(valorTotal));
					txtValorPago.setText("R$" + df.format(valorPago));
					txtValorRestante.setText("R$" + df.format(valorAPagar));
				}
			}
		});

		atualizarComandas(tabelaComandas, lblQuantidade);
		getNewRenderedTable(tabelaComandas);

	}
}
