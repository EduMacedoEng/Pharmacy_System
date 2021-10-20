package br.com.pharmacy.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.pharmacy.domain.Fornecedores;
import br.com.pharmacy.factory.ConexaoFactory;

public class FornecedoresDAO {
	public void salvar(Fornecedores f) throws SQLException {
		StringBuilder query = new StringBuilder();
		
		query.append("INSERT INTO fornecedores (descricao) ");
		query.append("VALUES (?)");
		
		Connection conexao = ConexaoFactory.conectar();
		
		// Method that allow to call sql commands
		PreparedStatement comando = conexao.prepareStatement(query.toString());
		
		comando.setString(1, f.getDescricao());
		comando.executeUpdate();
	}
	
	public void deletar(Fornecedores f) throws SQLException {
		StringBuilder query = new StringBuilder();
		
		query.append("DELETE FROM fornecedores ");
		query.append("WHERE codigo = ? ");
		
		Connection conexao = ConexaoFactory.conectar();
		
		// Method that allow to call sql commands
		PreparedStatement comando = conexao.prepareStatement(query.toString());
		
		comando.setLong(1, f.getCodigo());
		comando.executeUpdate();
	}
	
	public void editar(Fornecedores f) throws SQLException {
		StringBuilder query = new StringBuilder();
		
		query.append("UPDATE fornecedores ");
		query.append("SET descricao = ? ");
		query.append("WHERE codigo = ? ");
		
		Connection conexao = ConexaoFactory.conectar();
		
		// Method that allow to call sql commands
		PreparedStatement comando = conexao.prepareStatement(query.toString());
		
		comando.setString(1, f.getDescricao());
		comando.setLong(2, f.getCodigo());
		
		comando.executeUpdate();
	}
}
