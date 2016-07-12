package forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import dao.ComandaDAO;
import model.ItemComanda;
import model.Pagamento;

import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.Component;

public class FrmComandas extends ComandaDAO {

	private JFrame formComandas;
	private JScrollPane scrollComanda;
	private JTable tabelaComandas;
	private JTextArea textCompro;
	private static JTable tabelaItensComanda;
	private JScrollPane scrollItensComanda;
	private JLabel lblNewLabel;
	private JTextField txtValorTotal;
	private JTextField txtValorPago;
	private JLabel lblValorPago;
	private JLabel lblQuantidade;
	private JTextField txtValorRestante;
	private JLabel lblPagar;
	private JButton btnFecharComanda;
	private JButton btnEfetuarPagamento;
	private DecimalFormat df = new DecimalFormat("0.00");
	private ItemComanda itemComanda = new ItemComanda();
	private Pagamento pagamento = new Pagamento();
	private JLabel lblNewLabel_1;
	private JLabel label;

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
		formComandas.setResizable(false);
		formComandas.getContentPane().setBackground(Color.WHITE);
		formComandas.setTitle("BAR DO BUG\u00C3O");
		formComandas.setBounds(100, 100, 1274, 702);
		formComandas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		formComandas.getContentPane().setLayout(null);
		
		JScrollPane scrollComprovante = new JScrollPane(textCompro);
		scrollComprovante.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollComprovante.setBounds(1008, 186, 196, 357);
		formComandas.getContentPane().add(scrollComprovante);
		
		textCompro = new JTextArea();
		scrollComprovante.setViewportView(textCompro);
		textCompro.setBounds(0, 0, 4, 22);
		//formComandas.getContentPane().add(textArea);

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
		scrollComanda.setBounds(7, 186, 307, 429);
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
		tabelaItensComanda.getColumnModel().getColumn(0).setPreferredWidth(40);
		tabelaItensComanda.getTableHeader().setReorderingAllowed(false);
		tabelaItensComanda.getColumnModel().getColumn(3).setPreferredWidth(35);
		tabelaItensComanda.getColumnModel().getColumn(4).setPreferredWidth(30);
		tabelaItensComanda.getColumnModel().getColumn(5).setMaxWidth(0);
		tabelaItensComanda.getColumnModel().getColumn(5).setMinWidth(0);
		tabelaItensComanda.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
		tabelaItensComanda.getColumnModel().getColumn(6).setMaxWidth(20);
		tabelaItensComanda.getColumnModel().getColumn(6).setMinWidth(20);


		scrollComanda = new JScrollPane(tabelaItensComanda);
		scrollComanda.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollComanda.setBounds(10, 25, 307, 437);
		scrollItensComanda = new JScrollPane(tabelaItensComanda);
		scrollItensComanda.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollItensComanda.setBounds(324, 186, 674, 357);
		formComandas.getContentPane().add(scrollItensComanda);

		lblNewLabel_1 = new JLabel("");
		scrollItensComanda.setColumnHeaderView(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon(FrmComandas.class.getResource("/imagens/logoNovo350.png")));

		JLabel lblQtdComandas = new JLabel("Comandas abertas: ");
		lblQtdComandas.setForeground(Color.BLACK);
		lblQtdComandas.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblQtdComandas.setBounds(7, 158, 115, 33);
		formComandas.getContentPane().add(lblQtdComandas);

		lblQuantidade = new JLabel("0");
		lblQuantidade.setForeground(Color.BLACK);
		lblQuantidade.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblQuantidade.setBounds(115, 161, 40, 26);
		formComandas.getContentPane().add(lblQuantidade);

		JLabel lblFundo = new JLabel("");
		lblFundo.setIcon(new ImageIcon(FrmComandas.class.getResource("/imagens/faixa2.png")));
		lblFundo.setBounds(11, 0, 1015, 190);
		formComandas.getContentPane().add(lblFundo);

		JPanel panel = new JPanel();
		panel.setBounds(324, 554, 674, 109);
		formComandas.getContentPane().add(panel);
		panel.setLayout(null);

		lblPagar = new JLabel("\u00C0 pagar");
		lblPagar.setBounds(493, 2, 66, 33);
		panel.add(lblPagar);
		lblPagar.setForeground(new Color(0, 0, 255));
		lblPagar.setFont(new Font("Trebuchet MS", Font.BOLD, 16));

		txtValorPago = new JTextField();
		txtValorPago.setText("R$0,00");
		txtValorPago.setBounds(561, 42, 103, 26);
		panel.add(txtValorPago);
		txtValorPago.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		txtValorPago.setEditable(false);
		txtValorPago.setColumns(10);
		txtValorPago.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtValorPago.setBackground(Color.WHITE);

		lblValorPago = new JLabel("Valor pago");
		lblValorPago.setBounds(471, 37, 88, 33);
		panel.add(lblValorPago);
		lblValorPago.setForeground(new Color(0, 128, 0));
		lblValorPago.setFont(new Font("Trebuchet MS", Font.BOLD, 16));

		txtValorTotal = new JTextField();
		txtValorTotal.setText("R$0,00");
		txtValorTotal.setBounds(561, 75, 103, 26);
		panel.add(txtValorTotal);
		txtValorTotal.setBackground(Color.WHITE);
		txtValorTotal.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtValorTotal.setEditable(false);
		txtValorTotal.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		txtValorTotal.setColumns(10);

		lblNewLabel = new JLabel("Valor total");
		lblNewLabel.setBounds(471, 72, 88, 33);
		panel.add(lblNewLabel);
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 16));

		txtValorRestante = new JTextField();
		txtValorRestante.setText("R$0,00");
		txtValorRestante.setBounds(561, 9, 103, 26);
		panel.add(txtValorRestante);
		txtValorRestante.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		txtValorRestante.setEditable(false);
		txtValorRestante.setColumns(10);
		txtValorRestante.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtValorRestante.setBackground(Color.WHITE);

		btnFecharComanda = new JButton("Fechar a conta");
		btnFecharComanda.setBounds(9, 9, 131, 41);
		panel.add(btnFecharComanda);
		btnFecharComanda.setEnabled(false);

		btnEfetuarPagamento = new JButton("Efetuar pagamento");
		btnEfetuarPagamento.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnEfetuarPagamento.setBounds(7, 59, 131, 41);
		panel.add(btnEfetuarPagamento);

		btnEfetuarPagamento.setEnabled(false);

		label = new JLabel("");
		label.setIcon(new ImageIcon(FrmComandas.class.getResource("/imagens/logoNovo251.png")));
		label.setBounds(186, 0, 251, 107);
		panel.add(label);

		JButton btnAtualizarComanda = new JButton("Atualizar");
		btnAtualizarComanda.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnAtualizarComanda.setBounds(84, 622, 131, 41);
		formComandas.getContentPane().add(btnAtualizarComanda);

		atualizarComandas(tabelaComandas, lblQuantidade);
		getNewRenderedTable(tabelaComandas);

		tabelaComandas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				DefaultTableModel model = (DefaultTableModel) tabelaComandas.getModel();
				if (model.getRowCount() > 0) {
					if (tabelaComandas.getSelectedRow() >= 0) {						
						int numeroComanda = Integer.parseInt(
								(String) tabelaComandas.getModel().getValueAt(tabelaComandas.getSelectedRow(), 0));
						comprovante(textCompro,numeroComanda);
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
		btnAtualizarComanda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				atualizarComandas(tabelaComandas, lblQuantidade);
				getNewRenderedTable(tabelaComandas);

				txtValorTotal.setText("R$" + df.format(0.00));
				txtValorPago.setText("R$" + df.format(0.00));
				txtValorRestante.setText("R$" + df.format(0.00));
				limparTabela(tabelaItensComanda);

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
		btnEfetuarPagamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int numeroComanda = Integer
						.parseInt((String) tabelaComandas.getModel().getValueAt(tabelaComandas.getSelectedRow(), 0));
				boolean efetuarPagamento = pagamento.efetuarPagamentos(tabelaComandas);

				if (efetuarPagamento) {
					JOptionPane.showMessageDialog(null, "Pagamento efetuado", "Bar do Bugão",
							JOptionPane.INFORMATION_MESSAGE);
					float valorTotal = atualizarItensComanda(tabelaItensComanda, numeroComanda);
					float valorPago = valorAPagar(numeroComanda);
					float valorAPagar = valorTotal - valorPago;
					txtValorTotal.setText("R$" + df.format(valorTotal));
					txtValorPago.setText("R$" + df.format(valorPago));
					txtValorRestante.setText("R$" + df.format(valorAPagar));

				} else {
					JOptionPane.showMessageDialog(null, "Erro ao efetuar o pagamento, tente novamente", "Bar do Bugão",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
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
	}
}
