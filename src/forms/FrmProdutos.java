package forms;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.sun.xml.internal.txw2.output.TXWResult;

import dao.CategoriaDAO;
import dao.ProdutosDAO;
import model.Categoria;
import model.Produtos;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Window.Type;
import java.awt.Font;

public class FrmProdutos extends ProdutosDAO {

	private JScrollPane scrollProdutos;
	private JTable tabelaProdutos;
	public JFrame formProdutos;
	private JTextField txtNomeProd;
	private JTextField txtPreco;
	private JTextField txtObservacao;
	private JComboBox<String> cboCategoria;
	private CategoriaDAO categoriaDAO = new CategoriaDAO();

	private ArrayList<Categoria> arrayCategoria;
	private int auxiliar = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmProdutos window = new FrmProdutos();
					window.formProdutos.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FrmProdutos() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		formProdutos = new JFrame();
		formProdutos.setTitle("Cadastro de produtos");
		formProdutos.setType(Type.UTILITY);
		formProdutos.setResizable(false);
		formProdutos.setBounds(100, 100, 801, 350);
		formProdutos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		formProdutos.getContentPane().setLayout(null);

		tabelaProdutos = new JTable(0, 0);
		tabelaProdutos.setBounds(50, 50, 50, 50);
		tabelaProdutos.setSurrendersFocusOnKeystroke(true);
		tabelaProdutos.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "Cod", "Nome", "Categoria", "Obs.", "Preço" }) {
					public boolean isCellEditable(int row, int col) {
						return false;
					}
				});

		tabelaProdutos.getTableHeader().setResizingAllowed(false);
		tabelaProdutos.getTableHeader().setReorderingAllowed(false);
		tabelaProdutos.getColumnModel().getColumn(0).setPreferredWidth(30);
		tabelaProdutos.getColumnModel().getColumn(4).setPreferredWidth(30);
		
		scrollProdutos = new JScrollPane(tabelaProdutos);
		scrollProdutos.setBounds(286, 11, 499, 307);
		formProdutos.getContentPane().add(scrollProdutos);

		cboCategoria = new JComboBox<String>();
		cboCategoria.setBounds(15, 237, 148, 26);
		formProdutos.getContentPane().add(cboCategoria);

		JButton btnNovo = new JButton("Novo");
		btnNovo.setBounds(15, 295, 70, 23);
		formProdutos.getContentPane().add(btnNovo);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				novoProduto();
			}
		});
		btnSalvar.setBounds(106, 295, 70, 23);
		formProdutos.getContentPane().add(btnSalvar);

		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(198, 295, 76, 23);
		formProdutos.getContentPane().add(btnEditar);

		txtNomeProd = new JTextField();
		txtNomeProd.setBounds(15, 179, 116, 20);
		formProdutos.getContentPane().add(txtNomeProd);
		txtNomeProd.setColumns(10);

		txtPreco = new JTextField();
		txtPreco.setColumns(10);
		txtPreco.setBounds(188, 237, 86, 20);
		formProdutos.getContentPane().add(txtPreco);

		txtObservacao = new JTextField();
		txtObservacao.setColumns(10);
		txtObservacao.setBounds(141, 179, 133, 20);
		formProdutos.getContentPane().add(txtObservacao);

		JLabel lblNomeDoProduto = new JLabel("Nome do Produto");
		lblNomeDoProduto.setBounds(15, 162, 116, 14);
		formProdutos.getContentPane().add(lblNomeDoProduto);

		JLabel lblObs = new JLabel("Observa\u00E7\u00E3o");
		lblObs.setBounds(141, 162, 116, 14);
		formProdutos.getContentPane().add(lblObs);

		JLabel lblPreco = new JLabel("Pre\u00E7o");
		lblPreco.setBounds(188, 220, 86, 14);
		formProdutos.getContentPane().add(lblPreco);

		JLabel lblCat = new JLabel("Categoria");
		lblCat.setBounds(15, 220, 86, 14);
		formProdutos.getContentPane().add(lblCat);

		cboCategoria.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (auxiliar == 0) {
					int posicao = cboCategoria.getSelectedIndex();
					System.out.println(arrayCategoria.get(posicao).getCodCategoria());
					auxiliar++;
				} else {
					auxiliar = 0;
				}
			}
		});

		arrayCategoria = categoriaDAO.listarCategorias();
		categoriaDAO.preencherCombo(arrayCategoria, cboCategoria);
		preecherTabela(tabelaProdutos);
	}

	public void novoProduto() {
		String descProd = txtNomeProd.getText().toUpperCase();
		Float preco = Float.valueOf(txtPreco.getText().replaceAll(",", "."));
		int posicaoArray = cboCategoria.getSelectedIndex();
		int codCategoria = arrayCategoria.get(posicaoArray).getCodCategoria();
		String observacoes = txtObservacao.getText();
		Produtos produtos = new Produtos(codCategoria, descProd, preco, observacoes);
		try {
			boolean inserir = inserirProduto(produtos);

			if (inserir) {
				JOptionPane.showMessageDialog(null, "Produto cadastrado", "Bar do Bugão",
						JOptionPane.INFORMATION_MESSAGE);
				preecherTabela(tabelaProdutos);
			} else {
				JOptionPane.showMessageDialog(null, "OPS! Ocorreu um erro, tente novamente", "Bar do Bugão",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
