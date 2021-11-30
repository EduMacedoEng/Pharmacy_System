package br.com.pharmacy.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.pharmacy.domain.Fornecedores;
import br.com.pharmacy.domain.Produtos;
import br.com.pharmacy.factory.ConexaoFactory;

public class ProdutosDAO {
	public void salvar(Produtos p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO produtos (descricao, preco, quantidade, fornecedores_codigo)");
		sql.append("VALUES (?, ?, ?, ?)");
		
		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setString(1, p.getDescricao());
		comando.setDouble(2, p.getPreco());
		comando.setLong(3, p.getQuantidade());
		comando.setLong(4, p.getFornecedores().getCodigo());
		comando.executeUpdate();
	}
	
	public ArrayList<Produtos> listar() throws SQLException {
		StringBuilder query = new StringBuilder();
		
		query.append("SELECT p.codigo, p.descricao, p.preco, p.quantidade, f.codigo, f.descricao ");
		query.append("FROM produtos p ");
		query.append("INNER JOIN fornecedores f on p.fornecedores_codigo = f.codigo ");
		
		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(query.toString());
		
		ResultSet resultado = comando.executeQuery();
		ArrayList<Produtos> listaForn = new ArrayList<Produtos>();
		
		// If have next results to continue in while
		while(resultado.next()) {
			Fornecedores f = new Fornecedores();
			Produtos p = new Produtos();
			
			f.setCodigo(resultado.getLong("f.codigo"));
			f.setDescricao(resultado.getString("f.descricao"));
			p.setCodigo(resultado.getLong("p.codigo"));
			p.setDescricao(resultado.getString("p.descricao"));
			p.setPreco(resultado.getDouble("p.preco"));
			p.setQuantidade(resultado.getLong("p.quantidade"));
			p.setFornecedores(f);
			
			
			listaForn.add(p);
		}
		
		return listaForn;
	}

	public void deletar(Produtos p) throws SQLException {
		StringBuilder query = new StringBuilder();
		
		query.append("DELETE FROM produtos ");
		query.append("WHERE codigo = ? ");
		
		Connection conexao = ConexaoFactory.conectar();
		
		// Method that allow to call sql commands
		PreparedStatement comando = conexao.prepareStatement(query.toString());
		
		comando.setLong(1, p.getCodigo());
		comando.executeUpdate();
	}

	public void editar(Produtos p) throws SQLException {
		StringBuilder query = new StringBuilder();
		
		query.append("UPDATE produtos ");
		query.append("SET descricao = ?, preco = ?, quantidade = ?, fornecedores_codigo = ? ");
		query.append("WHERE codigo = ? ");
		
		Connection conexao = ConexaoFactory.conectar();
		
		// Method that allow to call sql commands
		PreparedStatement comando = conexao.prepareStatement(query.toString());
		
		comando.setString(1, p.getDescricao());
		comando.setDouble(2, p.getPreco());
		comando.setLong(3, p.getQuantidade());
		comando.setLong(4, p.getFornecedores().getCodigo());
		comando.setLong(5, p.getCodigo());
		
		comando.executeUpdate();
	}

	public Produtos buscarPorCodigo(Produtos p) throws SQLException {
		StringBuilder query = new StringBuilder();
		Produtos pRetorno = new Produtos();
		
		query.append("SELECT codigo, descricao, preco, quantidade ");
		query.append("FROM produtos ");
		query.append("WHERE codigo = ? ");
		query.append("ORDER BY descricao ASC");
		
		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(query.toString());
		
		comando.setLong(1, p.getCodigo());
		
		ResultSet resultado = comando.executeQuery();
		
		if (resultado.next()) {
			pRetorno.setCodigo(resultado.getLong("codigo"));
			pRetorno.setDescricao(resultado.getString("descricao"));
			pRetorno.setPreco(resultado.getDouble("preco"));
			pRetorno.setQuantidade(resultado.getLong("quantidade"));
			
			return pRetorno;
		}
		return pRetorno;
	}
	
	public ArrayList<Produtos> buscarPorDescricao(Produtos p) throws SQLException {
		StringBuilder query = new StringBuilder();
		
		query.append("SELECT codigo, descricao, preco, quantidade ");
		query.append("FROM produtos ");
		query.append("WHERE descricao like ? ");
		query.append("ORDER BY descricao ASC ");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(query.toString());
		
		comando.setString(1, "%" + p.getDescricao() + "%");
		
		ResultSet resultado = comando.executeQuery();
		ArrayList<Produtos> listaProd = new ArrayList<Produtos>();
		
		while(resultado.next()) {
			Produtos item = new Produtos();
			item.setCodigo(resultado.getLong("codigo"));
			item.setDescricao(resultado.getString("descricao"));
			item.setPreco(resultado.getDouble("preco"));
			item.setQuantidade(resultado.getLong("quantidade"));
			
			listaProd.add(item);
		}
		
		return listaProd;
	}
}
