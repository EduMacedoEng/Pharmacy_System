package br.com.pharmacy.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

	public Fornecedores buscaPorCodigo(Fornecedores f) throws SQLException {
		StringBuilder query = new StringBuilder();
		Fornecedores fRetorno = new Fornecedores();
		
		query.append("SELECT codigo, descricao ");
		query.append("FROM fornecedores ");
		query.append("WHERE codigo = ? ");
		query.append("ORDER BY descricao ASC ");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(query.toString());
		
		comando.setLong(1, f.getCodigo());
		ResultSet resultado = comando.executeQuery();
		
		if (resultado.next()) { // If exists results
			fRetorno.setCodigo(resultado.getLong("codigo"));
			fRetorno.setDescricao(resultado.getString("descricao"));
			
			return fRetorno;
		}
		
		return fRetorno ;
	}

	public ArrayList<Fornecedores> buscarPorDescricao(Fornecedores f) throws SQLException {
		StringBuilder query = new StringBuilder();
		
		query.append("SELECT codigo, descricao ");
		query.append("FROM fornecedores ");
		query.append("WHERE descricao like ? ");
		query.append("ORDER BY descricao ASC ");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(query.toString());
		
		comando.setString(1, "%" + f.getDescricao() + "%");
		
		ResultSet resultado = comando.executeQuery();
		ArrayList<Fornecedores> listaForn = new ArrayList<Fornecedores>();
		
		while(resultado.next()) {
			Fornecedores item = new Fornecedores();
			item.setCodigo(resultado.getLong("codigo"));
			item.setDescricao(resultado.getString("descricao"));
			
			listaForn.add(item);
		}
		
		return listaForn;
	}

	public ArrayList<Fornecedores> listar() throws SQLException {
		StringBuilder query = new StringBuilder();
		
		query.append("SELECT codigo, descricao ");
		query.append("FROM fornecedores ");
		query.append("ORDER BY descricao ASC ");
		
		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(query.toString());
		
		ResultSet resultado = comando.executeQuery();
		ArrayList<Fornecedores> listaForn = new ArrayList<Fornecedores>();
		
		while(resultado.next()) {
			Fornecedores f = new Fornecedores();
			f.setCodigo(resultado.getLong("codigo"));
			f.setDescricao(resultado.getString("descricao"));
			
			listaForn.add(f);
		}
		
		return listaForn;
	}	
}
