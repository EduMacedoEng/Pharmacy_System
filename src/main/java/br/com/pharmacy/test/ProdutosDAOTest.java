package br.com.pharmacy.test;

import java.sql.SQLException;

import org.junit.Test;

import br.com.pharmacy.DAO.ProdutosDAO;
import br.com.pharmacy.domain.Fornecedores;
import br.com.pharmacy.domain.Produtos;

public class ProdutosDAOTest {
	
	@Test
	public void salvar() throws SQLException {
		Produtos p1 = new Produtos();
		Fornecedores f1 = new Fornecedores();
		ProdutosDAO pdao = new ProdutosDAO();
		
		
		p1.setDescricao("Dipirona");
		p1.setPreco(2.99);
		p1.setQuantidade(12L);
		
		f1.setCodigo(34L);
		p1.setFornecedores(f1);
		
		pdao.salvar(p1);
	}
}
