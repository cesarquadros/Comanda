package model;

import java.sql.SQLException;

import dao.Conexao;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Conexao conexao = new Conexao();
		
		try {
			conexao.abreConexao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
