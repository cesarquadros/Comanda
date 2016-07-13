package forms;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;

import com.sun.xml.internal.txw2.output.TXWResult;

import dao.CategoriaDAO;
import dao.ProdutosDAO;
import model.Categoria;
import model.Produtos;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmProdutos extends ProdutosDAO{

	public JFrame formProdutos;
	private JTextField txtNomeProd;
	private JTextField txtPreco;
	private JTextField txtObservacao;
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
		formProdutos.setBounds(100, 100, 630, 314);
		formProdutos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		formProdutos.getContentPane().setLayout(null);
		
		JComboBox<String> cboCategoria = new JComboBox<String>();
		cboCategoria.setBounds(10, 180, 148, 26);
		formProdutos.getContentPane().add(cboCategoria);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.setBounds(10, 238, 89, 23);
		formProdutos.getContentPane().add(btnNovo);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String descProd = txtNomeProd.getText();
				Float preco = Float.valueOf(txtPreco.getText());
				int posicaoArray = cboCategoria.getSelectedIndex();
				int codCategoria = arrayCategoria.get(posicaoArray).getCodCategoria();
				String observacoes = txtObservacao.getText();
				Produtos produtos = new Produtos(codCategoria, descProd, preco, observacoes);
				try {
					boolean inserir = inserirProduto(produtos);
					
					if(inserir){
						JOptionPane.showMessageDialog(null, "Produto cadastrado", "Bar do Bugão",
								JOptionPane.INFORMATION_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(null, "OPS! Ocorreu um erro, tente novamente", "Bar do Bugão",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnSalvar.setBounds(208, 238, 89, 23);
		formProdutos.getContentPane().add(btnSalvar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(109, 238, 89, 23);
		formProdutos.getContentPane().add(btnEditar);
		
		txtNomeProd = new JTextField();
		txtNomeProd.setBounds(10, 122, 116, 20);
		formProdutos.getContentPane().add(txtNomeProd);
		txtNomeProd.setColumns(10);
		
		txtPreco = new JTextField();
		txtPreco.setColumns(10);
		txtPreco.setBounds(183, 180, 86, 20);
		formProdutos.getContentPane().add(txtPreco);
		
		txtObservacao = new JTextField();
		txtObservacao.setColumns(10);
		txtObservacao.setBounds(136, 122, 133, 20);
		formProdutos.getContentPane().add(txtObservacao);
		
		JLabel lblNomeDoProduto = new JLabel("Nome do Produto");
		lblNomeDoProduto.setBounds(10, 105, 116, 14);
		formProdutos.getContentPane().add(lblNomeDoProduto);
		
		JLabel lblObs = new JLabel("Observa\u00E7\u00E3o");
		lblObs.setBounds(136, 105, 116, 14);
		formProdutos.getContentPane().add(lblObs);
		
		JLabel lblPreco = new JLabel("Pre\u00E7o");
		lblPreco.setBounds(183, 163, 86, 14);
		formProdutos.getContentPane().add(lblPreco);
		
		JLabel lblCat = new JLabel("Categoria");
		lblCat.setBounds(10, 163, 86, 14);
		formProdutos.getContentPane().add(lblCat);
		
		cboCategoria.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(auxiliar == 0){
					int posicao = cboCategoria.getSelectedIndex();
					System.out.println(arrayCategoria.get(posicao).getCodCategoria());
					auxiliar ++;
				}else{
					auxiliar = 0;
				}
				
			}
		});
		
		arrayCategoria = categoriaDAO.listarCategorias();
		categoriaDAO.preencherCombo(arrayCategoria, cboCategoria);	
		
	}
	
	
}
