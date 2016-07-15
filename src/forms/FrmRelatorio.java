package forms;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.sun.glass.events.KeyEvent;
import dao.ComandaDAO;
import dao.ItemComandaDAO;
import dao.RelatorioDAO;
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

public class FrmRelatorio extends RelatorioDAO {

	static JFrame formRelatorio;
	private JScrollPane scrollComanda;
	private JTable tabelaComandas;
	private JTextArea textCompro;
	private static JTable tabelaItensComanda;
	private JScrollPane scrollItensComanda;
	private JLabel lblQuantidade;
	private DecimalFormat df = new DecimalFormat("0.00");
	private JLabel lblNewLabel_1;
	private ItemComandaDAO itemComandaDAO = new ItemComandaDAO();
	private ItemComanda itemComanda = new ItemComanda();
	private Pagamento pagamento = new Pagamento();
	private Comanda comanda;
	private ComandaDAO comandaDAO = new ComandaDAO();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRelatorio window = new FrmRelatorio();
					window.formRelatorio.setVisible(true);
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
	public FrmRelatorio() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws SQLException
	 */
	private void initialize() throws SQLException {
		formRelatorio = new JFrame();
		formRelatorio.setResizable(false);
		formRelatorio.getContentPane().setBackground(Color.WHITE);
		formRelatorio.setTitle("Comanda");
		formRelatorio.setBounds(100, 100, 1267, 589);
		formRelatorio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		formRelatorio.getContentPane().setLayout(null);

		JScrollPane scrollComprovante = new JScrollPane(textCompro);
		scrollComprovante.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollComprovante.setBounds(1008, 186, 238, 357);
		formRelatorio.getContentPane().add(scrollComprovante);

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

		tabelaComandas.getColumnModel().getColumn(1).setPreferredWidth(100);

		scrollComanda = new JScrollPane(tabelaComandas);
		scrollComanda.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollComanda.setBounds(7, 186, 343, 357);
		formRelatorio.getContentPane().add(scrollComanda);

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
		scrollItensComanda.setBounds(360, 186, 638, 357);
		formRelatorio.getContentPane().add(scrollItensComanda);

		lblNewLabel_1 = new JLabel("");
		scrollItensComanda.setColumnHeaderView(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon(FrmRelatorio.class.getResource("/imagens/logoNovo350.png")));

		JLabel lblQtdComandas = new JLabel("Comandas abertas: ");
		lblQtdComandas.setForeground(Color.BLACK);
		lblQtdComandas.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblQtdComandas.setBounds(7, 158, 115, 33);
		formRelatorio.getContentPane().add(lblQtdComandas);

		lblQuantidade = new JLabel("0");
		lblQuantidade.setForeground(Color.BLACK);
		lblQuantidade.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblQuantidade.setBounds(115, 161, 40, 26);
		formRelatorio.getContentPane().add(lblQuantidade);

		JLabel lblFundo = new JLabel("");
		lblFundo.setFont(new Font("Tahoma", Font.PLAIN, 99));
		lblFundo.setIcon(null);
		lblFundo.setBounds(130, 0, 991, 190);
		formRelatorio.getContentPane().add(lblFundo);

		atualizarComandas(tabelaComandas, lblQuantidade);

		comandaDAO.getNewRenderedTable(tabelaComandas);
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
	}

	// limpando o comprovante e os campos de valores
	public void limparComprovante() {

		textCompro.setText(null);
	}

	// atualiza a lista de comandas
	public void atualizarTabelaComanda() {
		try {
			// chamando o metodo na classe DAO para atualizar a lista de
			// comandas
			atualizarComandas(tabelaComandas, lblQuantidade);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// deixar as comandas abertas VERDE, e as fechadas VERMELHA
		comandaDAO.getNewRenderedTable(tabelaComandas);
		limparComprovante();
		limparTabela(tabelaItensComanda);
	}

	// tras os itens de uma comanda selecionada
	public void listarIntens() {
		int numeroComanda = Integer
				.parseInt((String) tabelaComandas.getModel().getValueAt(tabelaComandas.getSelectedRow(), 0));
		float valorTotal;
		try {
			valorTotal = itemComandaDAO.atualizarItensComanda(tabelaItensComanda, numeroComanda);
			comandaDAO.comprovante(textCompro, numeroComanda);
			textCompro.setText(textCompro.getText() + " VALOR A TOTAL R$" + df.format(valorTotal));
			textCompro.setText(textCompro.getText() + System.lineSeparator());
			tabelaItensComanda.getColumnModel().getColumn(6).setCellRenderer(
					new adicionarIcone((FrmRelatorio.class.getResource("/imagens/excluir.png")).toString()));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
