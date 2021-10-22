package br.com.pharmacy.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {
	private static final String USUARIO = "eduardo";
	private static final String SENHA = "Eduardo01.";
	private static final String URL = "jdbc:mysql://0.0.0.0:3306/sistema";
	
	public static Connection conectar() throws SQLException {
		
		// MySQL Driver reference for older JAVA versions
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		
		Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
		
		return conexao;
	}
	
	public static void main(String[] args) {
		try {
			Connection conexao = ConexaoFactory.conectar();
			System.out.println("Conectado com sucesso.");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Erro na conexão: " + e.getMessage());
		}
	}
}
