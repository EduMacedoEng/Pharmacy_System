package br.com.pharmacy.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.pharmacy.DAO.FornecedoresDAO;
import br.com.pharmacy.DAO.ProdutosDAO;
import br.com.pharmacy.domain.Fornecedores;
import br.com.pharmacy.domain.Produtos;
import br.com.pharmacy.util.JSFUtil;

@ManagedBean(name = "MBProdutos")
@ViewScoped
public class ProdutosBean {
	private ArrayList<Produtos> itens;
	private ArrayList<Produtos> itensFiltrados;
	private ArrayList<Fornecedores> comboFornecedores;
	
	private Produtos produtos;
	
	public ArrayList<Fornecedores> getComboFornecedores() {
		return comboFornecedores;
	}

	public void setComboFornecedores(ArrayList<Fornecedores> comboFornecedores) {
		this.comboFornecedores = comboFornecedores;
	}

	public Produtos getProdutos() {
		return produtos;
	}

	public void setProdutos(Produtos produtos) {
		this.produtos = produtos;
	}

	public ArrayList<Produtos> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Produtos> itens) {
		this.itens = itens;
	}
	
	public ArrayList<Produtos> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Produtos> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	@PostConstruct // When building it`ll also assemble the query prepararPesquisa()
	public void prepararPesquisa() {
		try {
			ProdutosDAO pdao = new ProdutosDAO();
			itens = pdao.listar();
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void prepararNovo() {
		
		try {
			produtos = new Produtos();
			
			FornecedoresDAO fdao = new FornecedoresDAO();
			comboFornecedores = fdao.listar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void novo() {
		try {
			ProdutosDAO pdao = new ProdutosDAO();
			pdao.salvar(produtos);
			
			// Here I allow the table when to reload to be updated.
			itens = pdao.listar();
			
			// The third step I need to call message method in JSFUtil class.
			JSFUtil.adicionarMensagemSucesso("Produto salvo com sucesso!");
		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.getStackTrace();
		}
	}
}
