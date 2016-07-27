package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class Conexao {
	private static String URL = "jdbc:sqlserver://localhost:1433;"+"databaseName=Comanda";
	private static String user;
	private static String password;
	// Esse é o nome do driver, que na internet você vai encontrar de varias
	// maneiras, mas só esse resolveu meus problemas
	private static String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	
	public static Connection abreConexao() throws SQLException {
		
		String userPassword[] = LoginBD.loginBd();
		user = userPassword[0];
		password = userPassword[1];		
		try {
			Class.forName(DRIVER);
			Connection con = DriverManager.getConnection(URL, user, password);		
			return con;
		} catch (ClassNotFoundException e) {
			System.out.println(e);
			throw new SQLException(e.getMessage());

		}
	}
}

