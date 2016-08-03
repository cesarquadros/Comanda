package forms;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.sun.glass.events.KeyEvent;
import dao.ComandaDAO;
import dao.ItemComandaDAO;
import model.Comanda;
import model.ItemComanda;
import model.Pagamento;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Robot;
import java.awt.AWTException;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComponent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

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
	private JButton btnAbrirComanda;
	private DecimalFormat df = new DecimalFormat("0.00");
	private JLabel lblNewLabel_1;
	private JMenu mnProdutos;
	private JLabel lblAtualizarComandas;
	private ItemComandaDAO itemComandaDAO = new ItemComandaDAO();
	private ItemComanda itemComanda = new ItemComanda();
	private Pagamento pagamento = new Pagamento();
	private Comanda comanda;

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
	 * 
	 * @throws SQLException
	 */
	public FrmComandas() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws SQLException
	 */
	private void initialize() throws SQLException {
		formComandas = new JFrame();
		formComandas.getContentPane().setBackground(Color.WHITE);
		formComandas.setTitle("Comanda");
		formComandas.setBounds(100, 100, 1262, 702);
		formComandas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		formComandas.getContentPane().setLayout(null);

		JScrollPane scrollComprovante = new JScrollPane(textCompro);
		scrollComprovante.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollComprovante.setBounds(1008, 186, 238, 357);
		formComandas.getContentPane().add(scrollComprovante);

		textCompro = new JTextArea();
		textCompro.setBackground(SystemColor.menu);
		textCompro.setEditable(false);
		textCompro.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		scrollComprovante.setViewportView(textCompro);
		textCompro.setBounds(0, 0, 4, 22);
		// formComandas.getContentPane().add(textArea);

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
		scrollComanda.setBounds(7, 186, 307, 357);
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

		// Bloqueia a rendenização das tabelas e reordenação das tabelas
		tabelaItensComanda.getTableHeader().setResizingAllowed(false);
		tabelaItensComanda.getTableHeader().setReorderingAllowed(false);

		tabelaItensComanda.getColumnModel().getColumn(0).setPreferredWidth(40);
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
		lblFundo.setFont(new Font("Tahoma", Font.PLAIN, 99));
		lblFundo.setIcon(null);
		lblFundo.setBounds(130, 0, 991, 190);
		formComandas.getContentPane().add(lblFundo);

		JPanel panel = new JPanel();
		panel.setBounds(324, 554, 922, 109);
		formComandas.getContentPane().add(panel);
		panel.setLayout(null);

		lblPagar = new JLabel("A pagar");
		lblPagar.setBounds(739, 2, 66, 33);
		panel.add(lblPagar);
		lblPagar.setForeground(new Color(0, 0, 255));
		lblPagar.setFont(new Font("Trebuchet MS", Font.BOLD, 16));

		txtValorPago = new JTextField();
		txtValorPago.setText("R$0,00");
		txtValorPago.setBounds(807, 42, 103, 26);
		panel.add(txtValorPago);
		txtValorPago.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		txtValorPago.setEditable(false);
		txtValorPago.setColumns(10);
		txtValorPago.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtValorPago.setBackground(Color.WHITE);

		lblValorPago = new JLabel("Valor pago");
		lblValorPago.setBounds(717, 37, 88, 33);
		panel.add(lblValorPago);
		lblValorPago.setForeground(new Color(0, 128, 0));
		lblValorPago.setFont(new Font("Trebuchet MS", Font.BOLD, 16));

		txtValorTotal = new JTextField();
		txtValorTotal.setText("R$0,00");
		txtValorTotal.setBounds(807, 75, 103, 26);
		panel.add(txtValorTotal);
		txtValorTotal.setBackground(Color.WHITE);
		txtValorTotal.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtValorTotal.setEditable(false);
		txtValorTotal.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		txtValorTotal.setColumns(10);

		lblNewLabel = new JLabel("Valor total");
		lblNewLabel.setBounds(717, 72, 88, 33);
		panel.add(lblNewLabel);
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 16));

		txtValorRestante = new JTextField();
		txtValorRestante.setText("R$0,00");
		txtValorRestante.setBounds(807, 9, 103, 26);
		panel.add(txtValorRestante);
		txtValorRestante.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		txtValorRestante.setEditable(false);
		txtValorRestante.setColumns(10);
		txtValorRestante.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtValorRestante.setBackground(Color.WHITE);

		btnFecharComanda = new JButton("Fechar a conta");
		btnFecharComanda.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnFecharComanda.setBounds(9, 60, 131, 41);
		panel.add(btnFecharComanda);
		btnFecharComanda.setEnabled(false);

		btnEfetuarPagamento = new JButton("Efetuar pagamento");
		btnEfetuarPagamento.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnEfetuarPagamento.setBounds(150, 60, 131, 41);
		panel.add(btnEfetuarPagamento);

		btnEfetuarPagamento.setEnabled(false);

		btnAbrirComanda = new JButton("Nova comanda");
		btnAbrirComanda.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAbrirComanda.setBounds(9, 9, 131, 41);
		panel.add(btnAbrirComanda);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1246, 21);
		formComandas.getContentPane().add(menuBar);

		mnProdutos = new JMenu("Produtos");
		menuBar.add(mnProdutos);

		JMenuItem mntmCadastrar = new JMenuItem("Cadastrar");
		mnProdutos.add(mntmCadastrar);
		
		JMenu mnRelatrios = new JMenu("Relat\u00F3rios");
		menuBar.add(mnRelatrios);
		
		JMenuItem mntmRelatrioDeComanda = new JMenuItem("Relat\u00F3rio de comanda");
		mnRelatrios.add(mntmRelatrioDeComanda);

		lblAtualizarComandas = new JLabel("F1 - Abre uma nova comanda");
		lblAtualizarComandas.setForeground(Color.BLACK);
		lblAtualizarComandas.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblAtualizarComandas.setBounds(17, 545, 235, 33);
		formComandas.getContentPane().add(lblAtualizarComandas);

		JLabel lblFatualizaAs = new JLabel("F5 - Atualiza as comandas em aberto");
		lblFatualizaAs.setForeground(Color.BLACK);
		lblFatualizaAs.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblFatualizaAs.setBounds(17, 565, 251, 33);
		formComandas.getContentPane().add(lblFatualizaAs);

		JLabel lblFFecha = new JLabel("F6 - Fecha a comanda selecionada");
		lblFFecha.setForeground(Color.BLACK);
		lblFFecha.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblFFecha.setBounds(17, 585, 285, 33);
		formComandas.getContentPane().add(lblFFecha);

		JLabel lblFEfetuar = new JLabel("F7 - Efetua pagamento na comanda selecionada");
		lblFEfetuar.setForeground(Color.BLACK);
		lblFEfetuar.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblFEfetuar.setBounds(17, 605, 297, 33);
		formComandas.getContentPane().add(lblFEfetuar);

		mntmCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmProdutos frmProdutos = new FrmProdutos();
				frmProdutos.formProdutos.setVisible(true);
			}
		});
		tabelaComandas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				DefaultTableModel model = (DefaultTableModel) tabelaComandas.getModel();
				if (model.getRowCount() > 0) {
					if (tabelaComandas.getSelectedRow() >= 0) {
						listarIntens();
					}
				}
			}
		});
		// Acoes de atalhos para executar as funcoes de atualizar comanda,
		// fechar comanda e efetuar pagamento
		tabelaComandas.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_F5) {
					atualizarTabelaComanda();
				} else if (e.getKeyCode() == KeyEvent.VK_F6) {
					fecharComanda();
				} else if (e.getKeyCode() == KeyEvent.VK_F7) {
					efetuarPagamento();
				} else if (e.getKeyCode() == KeyEvent.VK_F1) {
					abrirComanda();
				}
			}

			@Override
			public void keyReleased(java.awt.event.KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyTyped(java.awt.event.KeyEvent e) {
				// TODO Auto-generated method stub
			}
		});
		tabelaItensComanda.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent clique) {
				if (clique.getClickCount() == 2) {
					apagarItem();
					tabelaComandas.requestFocus();
				}
			}
		});
		btnEfetuarPagamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				efetuarPagamento();
			}
		});
		btnFecharComanda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fecharComanda();
			}
		});
		btnAbrirComanda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabelaComandas.requestFocus();
				abrirComanda();
			}
		});
		mntmRelatrioDeComanda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				FrmRelatorio frmRelatorio;
				try {
					frmRelatorio = new FrmRelatorio();
					frmRelatorio.formRelatorio.setVisible(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		});
		
		atualizarComandas(tabelaComandas, lblQuantidade);
		getNewRenderedTable(tabelaComandas);
	}

	//abrir uma nova comanda
	public void abrirComanda() {
		String nomeComanda = "";
		//pegando a data atual
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String dataAtual = dateFormat.format(new Date());

		//pegando o nome da comanda que será aberta
		while (nomeComanda.equals("")) {
			nomeComanda = JOptionPane.showInputDialog("Nome da comanda", null).toUpperCase();
		}
		//chamando o metodo de abrir comanda da classe DAO
		comanda = new Comanda(nomeComanda, dataAtual, "ABERTO", 0);
		boolean abrirComanda = abrirComanda(comanda);
		//se a comanda for aberta com sucesso retorna True, se não retorna false
		if (abrirComanda) {
			JOptionPane.showMessageDialog(null, "Comanda aberta para: " + nomeComanda, "Bar do Bugão",
					JOptionPane.INFORMATION_MESSAGE);
			try {
				//atualizando a tabela de comanda e limpando a tabela de itens e o comprovante
				atualizarComandas(tabelaComandas, lblQuantidade);
				limparTabela(tabelaItensComanda);
				limparComprovante();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(null, "OPS, ocorreu um erro ao abrir a comanda", "Bar do Bugão",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
	//limpando o comprovante e os campos de valores
	public void limparComprovante() {
		txtValorTotal.setText("R$" + df.format(0.00));
		txtValorPago.setText("R$" + df.format(0.00));
		txtValorRestante.setText("R$" + df.format(0.00));
		textCompro.setText(null);
	}
	// efetuar pagamento parcial ou total de uma comanda
	public void efetuarPagamento() {
		//pegando o numero da comanda selecionada
		int numeroComanda = Integer
				.parseInt((String) tabelaComandas.getModel().getValueAt(tabelaComandas.getSelectedRow(), 0));
		try {
			boolean efetuarPagamento = pagamento.efetuarPagamentos(tabelaComandas);
			if (efetuarPagamento) {
				JOptionPane.showMessageDialog(null, "Pagamento efetuado", "Bar do Bugão",
						JOptionPane.INFORMATION_MESSAGE);
				itemComandaDAO.atualizarItensComanda(tabelaItensComanda,numeroComanda);
				//pegando o valor total da comanda selecionada
				float valorTotal = itemComandaDAO.valorTotal(numeroComanda);	
				//pegando os valores pagos da comanda selecionada
				float valorPago = valorAPagar(numeroComanda);
				//subtraindo o valor pago do valor total
				float valorAPagar = valorTotal - valorPago;
				//preenchendo os campos com os valores
				txtValorTotal.setText("R$" + df.format(valorTotal));
				txtValorPago.setText("R$" + df.format(valorPago));
				txtValorRestante.setText("R$" + df.format(valorAPagar));
				//montando o comprovante
				comprovante(textCompro, numeroComanda);
				textCompro.setText(textCompro.getText() + " VALOR A PAGAR R$" + df.format(valorAPagar));
				textCompro.setText(textCompro.getText() + System.lineSeparator());
			} else {
				JOptionPane.showMessageDialog(null, "OPS, erro ao efetuar o pagamento, tente novamente", "Bar do Bugão",
						JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//atualiza a lista de comandas 
	public void atualizarTabelaComanda() {
		try {
			//chamando o metodo na classe DAO para atualizar a lista de comandas
			atualizarComandas(tabelaComandas, lblQuantidade);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//deixar as comandas abertas VERDE, e as fechadas VERMELHA
		getNewRenderedTable(tabelaComandas);
		limparComprovante();
		limparTabela(tabelaItensComanda);
	}
	//fechar uma comanda aberta
	public void fecharComanda() throws NumberFormatException {
		//égando o número da comanda selecionada
		String numeroComanda = (String) tabelaComandas.getModel().getValueAt(tabelaComandas.getSelectedRow(), 0);
		//confirmação do fechamento da comanda
		int confirmacao = JOptionPane.showConfirmDialog(null, "Deseja realmente fechar a conta?", "Bar do Bugão",JOptionPane.YES_NO_OPTION);
		
		float valorTotal = 0;
		float valorPago = 0 ;
		try {
			valorTotal = itemComandaDAO.valorTotal(Integer.parseInt(numeroComanda));
			valorPago = valorAPagar(Integer.parseInt(numeroComanda));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		if (confirmacao == JOptionPane.YES_OPTION) {
			while(valorPago < valorTotal){
				float valorRestante = valorTotal - valorPago;
				JOptionPane.showMessageDialog(null, "Resta pagar R$ "+valorRestante, "Bar do Bugão",
						JOptionPane.INFORMATION_MESSAGE);
				efetuarPagamento();
				try {
					valorPago = valorAPagar(Integer.parseInt(numeroComanda));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				//chamando o metodo na classe DAO para fechar a comanda, passando o status coo parametro
				fecharComanda(numeroComanda, "FECHADO");
				atualizarComandas(tabelaComandas, lblQuantidade);
				JOptionPane.showMessageDialog(null, "Comanda fechada", "Bar do Bugão",
						JOptionPane.INFORMATION_MESSAGE);
				limparComprovante();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			limparTabela(tabelaItensComanda);
			btnFecharComanda.setEnabled(false);
		}
	}
	//tras os itens de uma comanda selecionada
	public void listarIntens(){
		int numeroComanda = Integer.parseInt(
				(String) tabelaComandas.getModel().getValueAt(tabelaComandas.getSelectedRow(), 0));
		float valorTotal;
		try {
			itemComandaDAO.atualizarItensComanda(tabelaItensComanda,numeroComanda);
			valorTotal = itemComandaDAO.valorTotal(numeroComanda);
			float valorPago = valorAPagar(numeroComanda);
			float valorAPagar = valorTotal - valorPago;
			comprovante(textCompro, numeroComanda);
			textCompro.setText(textCompro.getText() + " VALOR A PAGAR R$" + df.format(valorAPagar));
			textCompro.setText(textCompro.getText() + System.lineSeparator());
			txtValorTotal.setText("R$" + df.format(valorTotal));
			txtValorPago.setText("R$" + df.format(valorPago));
			txtValorRestante.setText("R$" + df.format(valorAPagar));
			btnFecharComanda.setEnabled(true);
			btnEfetuarPagamento.setEnabled(true);
			tabelaItensComanda.getColumnModel().getColumn(6).setCellRenderer(new adicionarIcone(
					(FrmComandas.class.getResource("/imagens/excluir.png")).toString()));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	//apagar item de uma comanda
	public void apagarItem(){
		itemComanda.excluirItemComanda(tabelaItensComanda);
		int numeroComanda = Integer.parseInt(
				(String) tabelaComandas.getModel().getValueAt(tabelaComandas.getSelectedRow(), 0));
		float valorTotal;
		try {
			itemComandaDAO.atualizarItensComanda(tabelaItensComanda,numeroComanda);
			valorTotal = itemComandaDAO.valorTotal(numeroComanda);
			float valorPago = valorAPagar(numeroComanda);
			float valorAPagar = valorTotal - valorPago;
			txtValorTotal.setText("R$" + df.format(valorTotal));
			txtValorPago.setText("R$" + df.format(valorPago));
			txtValorRestante.setText("R$" + df.format(valorAPagar));
			txtValorTotal.setText("R$" + df.format(valorTotal));
			txtValorPago.setText("R$" + df.format(valorPago));
			txtValorRestante.setText("R$" + df.format(valorAPagar));
			comprovante(textCompro, numeroComanda);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
