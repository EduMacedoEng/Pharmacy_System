package br.com.pharmacy.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.pharmacy.domain.Produtos;
import br.com.pharmacy.factory.ConexaoFactory;

public class ProdutosDAO {
	public void salvar (Produtos p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO produtos (descricao, preco, quantidade, fornecedores_codigo)");
		sql.append("VALUES (?, ?, ?, ?)");
		
		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setString(1, p.getDescricao());
		comando.setDouble(2, p.getPreco());
		comando.setLong(2, p.getQuantidade());
		comando.setLong(3, p.getFornecedores().getCodigo());
		comando.executeUpdate();
	}
}
