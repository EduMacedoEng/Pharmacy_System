package br.com.pharmacy.test;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import br.com.pharmacy.DAO.FornecedoresDAO;
import br.com.pharmacy.DAO.ProdutosDAO;
import br.com.pharmacy.domain.Fornecedores;
import br.com.pharmacy.domain.Produtos;

public class ProdutosDAOTest {
	
	@Test
	public void salvar() throws SQLException {
		Produtos p1 = new Produtos();
		Fornecedores f1 = new Fornecedores();
		ProdutosDAO pdao = new ProdutosDAO();
		
		
		p1.setDescricao("Reconter 20 mg");
		p1.setPreco(50.99);
		p1.setQuantidade(23L);
		
		f1.setCodigo(42L);
		f1.setDescricao("Libbs");
		p1.setFornecedores(f1);
		
		pdao.salvar(p1);
	}
	
	@Test
	@Ignore
	public void listar() throws SQLException {
		ProdutosDAO pdao = new ProdutosDAO();
		ArrayList<Produtos> lista = pdao.listar();
		
		for (Produtos p : lista) {
			System.out.println(p);
		}
		
	}

	@Test
	@Ignore
	public void deletar() throws SQLException {
		ProdutosDAO pdao = new ProdutosDAO();
		ArrayList<Produtos> lista = pdao.listar();
		
		// Delete the last register inserted
		pdao.deletar(lista.get(lista.size()-1));
	}
	
	@Test
	@Ignore
	public void editar() throws SQLException {
		ProdutosDAO pdao = new ProdutosDAO();
		FornecedoresDAO fdao = new FornecedoresDAO();
		
		ArrayList<Produtos> prods = pdao.listar();
		ArrayList<Fornecedores> forns = fdao.listar();
		
		
		Produtos p = prods.get(prods.size()-1);
		Fornecedores f = forns.get(0);
		
		p.setDescricao("Epnefrina");
		p.setPreco(100.00);
		p.setQuantidade(20L);
		p.setFornecedores(f);
		
		pdao.editar(p);
	}
	
	@Test
	@Ignore
	public void buscarPorCodigo() throws SQLException{
		Produtos p = new Produtos();
		ProdutosDAO pdao = new ProdutosDAO();
		
		p.setCodigo(2L);
		pdao.buscarPorCodigo(p);
		
	}
	
	@Test
	@Ignore
	public void buscarPorDescricao() throws SQLException{
		Produtos p = new Produtos();
		ProdutosDAO pdao = new ProdutosDAO();
		
		p.setDescricao("Remedio");
		pdao.buscarPorDescricao(p);
	}
}
