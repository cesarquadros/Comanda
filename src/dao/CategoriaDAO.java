package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JComboBox;

import model.Categoria;

public class CategoriaDAO extends Conexao {

	private Connection con;
	private Statement statement;
	private ResultSet rs;
	private String sql;

	public ArrayList<Categoria> listarCategorias() {
		ArrayList<Categoria> arrayCategoria = new ArrayList<>();

		try {
			con = abreConexao();
			statement = con.createStatement();

			sql = "SELECT * FROM CATEGORIAS";
			rs = statement.executeQuery(sql);

			while (rs.next()) {
				String nomeCategoria = rs.getString(2);
				int idCategoria = rs.getInt(1);

				Categoria categoria = new Categoria(nomeCategoria, idCategoria);
				arrayCategoria.add(categoria);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayCategoria;
	}

	public void preencherCombo(ArrayList<Categoria> arrayCategoria, JComboBox<String> comboCategoria) {
		for (int i = 0; i < arrayCategoria.size(); i++) {
			comboCategoria.addItem((arrayCategoria.get(i).getCategoria()));

		}
	}
}
